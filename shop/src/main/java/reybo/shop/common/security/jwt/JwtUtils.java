package reybo.shop.common.security.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reybo.shop.client.AuthClient;
import reybo.shop.common.security.exception.AuthServiceNotFoundException;
import reybo.shop.common.security.exception.JwtTokenInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {

    private final AuthClient authClient;

    public UUID getUserIdFromJwt(String token) {
        try {
            if (!authClient.validateToken(token)) {
                log.info("Токен " + token + " не прошёл валидацию");
            }

            try {
                String[] parts = token.split("\\.");
                if (parts.length < 2) {
                    throw new JwtTokenInvalidException("Invalid JWT format");
                }
                String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

                JsonNode payload = new ObjectMapper().readTree(payloadJson);

                String userIdStr = payload.get("sub").asText();
                log.info("Токен c userId {} успешно прошел валидацию в Authentication Service", userIdStr);
                return UUID.fromString(userIdStr);
            } catch (Exception e) {
                System.err.println("Failed to parse JWT: " + e.getMessage());
                throw new JwtTokenInvalidException("Invalid JWT token");
            }
        } catch (Exception e) {
            log.info("Не удалось связаться с сервисом аутентификации или возникла ошибка в сервисе аутентификации");
            throw new AuthServiceNotFoundException("Не удалось связаться с сервисом аутентификации или возникла ошибка в сервисе аутентификации");
        }
    }
}