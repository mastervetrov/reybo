package reybo.shop.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reybo.shop.common.security.JwtUserDetailsImpl;
import reybo.shop.common.security.exception.AuthServiceNotFoundException;
import reybo.shop.common.security.exception.JwtTokenInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    // Имена cookies как в вашем контроллере
    private static final String ACCESS_TOKEN_COOKIE = "ACCESS_TOKEN";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getRequestURI();

        return path.startsWith("/shop/styles/css/") ||
                path.startsWith("/login") ||
                path.startsWith("/register") ||
                path.startsWith("/shop/header/media/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractTokenFromCookies(request);

        if (token != null) {
            try {
                UUID userId = jwtUtils.getUserIdFromJwt(token);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        new JwtUserDetailsImpl(userId, token),
                        null,
                        Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JwtTokenInvalidException e) {
                log.warn("Invalid JWT token from cookies: {}", e.getMessage());
            } catch (AuthServiceNotFoundException e) {
                log.error("Auth service unavailable: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Извлекает ACCESS_TOKEN из cookies
     */
    private String extractTokenFromCookies(HttpServletRequest request) {
        // Сначала пробуем из заголовка (если вдруг придет)
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        // Основной способ - из cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}