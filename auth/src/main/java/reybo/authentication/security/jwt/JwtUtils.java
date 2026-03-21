package reybo.authentication.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reybo.authentication.entities.user.User;
import reybo.authentication.security.AppUserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    private Duration tokenExpiration = Duration.ofMinutes(15);

    private JwtParser jwtParser;

    private SecretKey signingKey;

    /**
     * Инициализация ключа и парсера при первом использовании
     */
    private SecretKey getSigningKey() {
        if (signingKey == null) {
            synchronized (this) {
                if (signingKey == null) {
                    validateKeyLength();
                    byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
                    signingKey = Keys.hmacShaKeyFor(keyBytes);
                }
            }
        }
        return signingKey;
    }

    /**
     * Получение настроенного парсера JWT
     */
    private JwtParser getJwtParser() {
        if (jwtParser == null) {
            synchronized (this) {
                if (jwtParser == null) {
                    jwtParser = Jwts.parser()
                            .verifyWith(getSigningKey())
                            .clockSkewSeconds(10)
                            .requireIssuer("reybo-authentication") // Рекомендуется добавить issuer
                            .build();
                }
            }
        }
        return jwtParser;
    }

    /**
     * Проверка длины секретного ключа
     */
    private void validateKeyLength() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            log.error("JWT secret key is too short. Minimum 32 bytes required, got {} bytes.", keyBytes.length);
            throw new IllegalArgumentException(
                    "JWT secret must be at least 256 bits (32 bytes). " +
                            "Current key length: " + (keyBytes.length * 8) + " bits."
            );
        }
        log.debug("JWT key validated: {} bits", keyBytes.length * 8);
    }

    public String generateJwtToken(AppUserDetails userDetails) {
        return generateTokenFromUser(userDetails.getUser());
    }

    public String generateTokenFromUser(User user) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("userId", user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .claim("tokenType", "ACCESS")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(tokenExpiration)))
                .issuer("reybo-authentication")
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

//    /**
//     * Расширенная генерация токена с дополнительными claims
//     */
//    public String generateJwtToken(AppUserDetails userDetails, String tokenType) {
//        Instant now = Instant.now();
//
//        return Jwts.builder()
//                .subject(userDetails.getId().toString())
//                .issuer("reybo-authentication")
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(now.plus(tokenExpiration)))
//                .claim("tokenType", tokenType)
//                .claim("username", userDetails.getUsername())
//                .claim("authorities", userDetails.getAuthorities())
//                .signWith(getSigningKey())
//                .compact();
//    }

    public String getUserId(String token) {
        try {
            return getJwtParser()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            log.error("Failed to extract user ID from token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    /**
     * Получение всех claims из токена
     */
    public Claims getAllClaims(String token) {
        try {
            return getJwtParser()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            log.error("Failed to parse JWT claims: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    /**
     * Получение конкретного claim из токена
     */
    public <T> T getClaim(String token, String claimName, Class<T> requiredType) {
        return getAllClaims(token).get(claimName, requiredType);
    }

    /**
     * Получение типа токена
     */
    public String getTokenType(String token) {
        return getClaim(token, "tokenType", String.class);
    }

    public boolean validate(String authToken) {
        if (authToken == null || authToken.trim().isEmpty()) {
            log.debug("Empty or null token provided");
            return false;
        }

        try {
            Jws<Claims> jws = getJwtParser().parseSignedClaims(authToken);

            // Дополнительные проверки (опционально)
            Claims claims = jws.getPayload();
            return validateClaims(claims);

        } catch (SecurityException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token format: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("JWT token expired at {}", e.getClaims().getExpiration());
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT compact string is empty: {}", e.getMessage());
        } catch (JwtException e) {
            log.warn("JWT validation error: {}", e.getMessage());
        }

        return false;
    }

    /**
     * Дополнительная валидация claims
     */
    private boolean validateClaims(Claims claims) {
        // Проверка issuer (если установлен в парсере)
        String issuer = claims.getIssuer();
        if (issuer != null && !issuer.equals("reybo-authentication")) {
            log.warn("Invalid JWT issuer: {}", issuer);
            return false;
        }

        // Проверка subject (не должен быть пустым)
        String subject = claims.getSubject();
        if (subject == null || subject.trim().isEmpty()) {
            log.warn("JWT subject is empty");
            return false;
        }

        try {
            // Проверка, что subject - валидный UUID
            UUID.fromString(subject);
        } catch (IllegalArgumentException e) {
            log.warn("JWT subject is not a valid UUID: {}", subject);
            return false;
        }

        // Проверка типа токена (если есть)
        String tokenType = claims.get("tokenType", String.class);
        if (tokenType != null && !"ACCESS".equals(tokenType)) {
            log.warn("Invalid token type: {}", tokenType);
            return false;
        }

        return true;
    }

    /**
     * Получение времени истечения токена
     */
    public Date getExpirationDate(String token) {
        return getAllClaims(token).getExpiration();
    }

    /**
     * Проверка, истек ли токен
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDate(token);
        return expiration != null && expiration.before(new Date());
    }

    /**
     * Оставшееся время жизни токена в миллисекундах
     */
    public long getRemainingTimeMillis(String token) {
        try {
            Date expiration = getExpirationDate(token);
            if (expiration == null) {
                return 0;
            }
            return expiration.getTime() - System.currentTimeMillis();
        } catch (Exception e) {
            log.debug("Failed to get remaining time: {}", e.getMessage());
            return 0;
        }
    }

    /**
     * Очистка кешированных объектов (например, для тестирования)
     */
    public void clearCache() {
        synchronized (this) {
            jwtParser = null;
            signingKey = null;
        }
    }
}