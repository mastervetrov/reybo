package reybo.account.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reybo.account.client.AuthClient;
import reybo.account.service.UserActivityService;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private AuthClient authClient;

    @Autowired
    private UserActivityService userActivityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        log.info("🔐 [1] JwtAuthenticationFilter: Начало обработки запроса: {}", request.getRequestURI());

        String token = extractToken(request);

        if (token != null) {
            log.info("🔐 [2] Токен извлечен (длина: {})", token.length());

            try {
                log.info("🔐 [3] Отправляем валидацию в mc-authentication...");
                ResponseEntity<Boolean> validationResponse = authClient.validateToken(token);
                log.info("🔐 [4] Ответ от mc-authentication: статус={}, body={}",
                        validationResponse.getStatusCode(), validationResponse.getBody());

                if (validationResponse.getStatusCode().is2xxSuccessful() && Boolean.TRUE.equals(validationResponse.getBody())) {
                    log.info("🔐 [5] Токен валиден, извлекаем userId...");
                    UUID userId = extractUserIdFromTokenWithoutSignatureCheck(token);
                    log.info("✅ [6] Token validated. User ID: {}", userId);
                    userActivityService.recordUserActivity(userId);
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userId.toString(), token, Collections.emptyList()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("✅ [7] Аутентификация установлена в SecurityContext");

                    log.info("🔐 [8] Продолжаем цепочку фильтров");
                    chain.doFilter(request, response); // 👈 ТОЛЬКО ЗДЕСЬ!

                } else {
                    log.error("❌ [ERROR] Токен не прошел валидацию в сервисе аутентификации");
                    sendError(response, "Invalid token");
                }
            } catch (Exception e) {
                log.error("❌ [ERROR] Ошибка валидации токена: {}", e.getMessage());
                log.error("❌ Stack trace:", e);
                sendError(response, "Invalid or expired token");
            }
        } else {
            log.warn("⚠️ [WARN] Токен не найден в запросе");
            sendError(response, "No token provided");
        }
    }

    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"error\":\"%s\"}", message));
        // 👇 НЕ вызываем chain.doFilter!
    }


    private UUID extractUserIdFromTokenWithoutSignatureCheck(String token) {
        try {
            // Разделяем токен на части: header.payload.signature
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT token format");
            }


            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));

            ObjectMapper mapper = new ObjectMapper();

            java.util.Map<String, Object> claims = mapper.readValue(payloadJson,
                    new com.fasterxml.jackson.core.type.TypeReference<java.util.HashMap<String, Object>>() {});

            String subject = (String) claims.get("userId");

            if (subject == null || subject.isBlank()) {
                throw new RuntimeException("Subject (sub) is missing in the token");
            }

            return UUID.fromString(subject);

        } catch (Exception e) {
            log.error("Failed to extract user ID from token", e);
            throw new RuntimeException("Failed to extract user ID from token", e);
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7); // Удаляем "Bearer "
        }
        return null;
    }
}