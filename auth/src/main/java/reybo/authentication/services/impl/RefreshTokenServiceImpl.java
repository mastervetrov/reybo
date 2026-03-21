package reybo.authentication.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reybo.authentication.entities.RefreshToken;
import reybo.authentication.exceptions.RefreshTokenException;
import reybo.authentication.repositories.RefreshTokenRepository;
import reybo.authentication.services.RefreshTokenService;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final Duration EARLY_REFRESH_THRESHOLD = Duration.ofDays(3);
    private static final int BATCH_SIZE = 100;

    @Value("${app.jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(UUID userId) {
        Instant now = Instant.now();

        return refreshTokenRepository.findByUserId(userId)
                .map(existingToken -> handleExistingToken(existingToken, userId, now))
                .orElseGet(() -> createNewRefreshToken(userId, now));
    }

    @Override
    public RefreshToken checkRefreshToken(String tokenValue) {
        return refreshTokenRepository.findByToken(tokenValue)
                .map(this::validateRefreshToken)
                .orElseThrow(() -> new RefreshTokenException(tokenValue, "Refresh token not found"));
    }

    @Override
    @Transactional
    public void deleteByUserId(UUID userId) {
        int deletedCount = refreshTokenRepository.deleteByUserId(userId);
        log.debug("Deleted {} refresh token(s) for user {}", deletedCount, userId);
    }

    @Override
    @Transactional
    public void deleteRefreshToken(String tokenValue) {
        refreshTokenRepository.deleteByToken(tokenValue);
        log.debug("Deleted refresh token: {}", tokenValue);
    }

    @Override
    @Transactional
    public void rotateRefreshToken(String oldTokenValue, UUID userId) {
        deleteRefreshToken(oldTokenValue);
        createRefreshToken(userId);
        log.debug("Rotated refresh token for user {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isRefreshTokenValid(String tokenValue) {
        return refreshTokenRepository.findByToken(tokenValue)
                .map(token -> token.getExpiryDate().isAfter(Instant.now()))
                .orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveTokens() {
        return refreshTokenRepository.countByExpiryDateAfter(Instant.now());
    }

    @Scheduled(cron = "${app.refresh-token.cleanup-cron:0 0 2 * * ?}") // По умолчанию: каждый день в 2:00
    @Transactional
    public void cleanupExpiredTokens() {
        Instant now = Instant.now();
        long deletedCount = refreshTokenRepository.deleteByExpiryDateBefore(now);

        if (deletedCount > 0) {
            log.info("Cleaned up {} expired refresh tokens", deletedCount);
        }
    }

    /**
     * Создает новый refresh токен
     */
    private RefreshToken createNewRefreshToken(UUID userId, Instant creationTime) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(generateSecureToken())
                .expiryDate(calculateExpiryDate(creationTime))
                .createdAt(creationTime)
                .lastUsedAt(creationTime)
                .build();

        RefreshToken savedToken = refreshTokenRepository.save(refreshToken);
        log.info("Created new refresh token for user {}", userId);
        return savedToken;
    }

    /**
     * Обрабатывает существующий токен
     */
    private RefreshToken handleExistingToken(RefreshToken existingToken, UUID userId, Instant now) {
        if (isTokenStillValid(existingToken, now)) {
            Duration remainingDuration = Duration.between(now, existingToken.getExpiryDate());

            if (shouldRenewEarly(remainingDuration)) {
                log.info("Refresh token for user {} expires in {}, renewing...",
                        userId, formatDuration(remainingDuration));
                return renewRefreshToken(existingToken, userId, now);
            }

            log.debug("Using existing valid refresh token for user {}", userId);
            existingToken.setLastUsedAt(now);
            return refreshTokenRepository.save(existingToken);
        }

        log.info("Refresh token for user {} expired, creating new one...", userId);
        return renewRefreshToken(existingToken, userId, now);
    }

    /**
     * Обновляет существующий refresh токен
     */
    private RefreshToken renewRefreshToken(RefreshToken oldToken, UUID userId, Instant now) {
        // Удаляем старый токен
        refreshTokenRepository.delete(oldToken);

        // Создаем новый
        RefreshToken newToken = RefreshToken.builder()
                .userId(userId)
                .token(generateSecureToken())
                .expiryDate(calculateExpiryDate(now))
                .createdAt(now)
                .lastUsedAt(now)
                .previousTokenId(oldToken.getId()) // Сохраняем ссылку на предыдущий токен для аудита
                .build();

        RefreshToken savedToken = refreshTokenRepository.save(newToken);
        log.info("Renewed refresh token for user {}", userId);
        return savedToken;
    }

    /**
     * Валидирует refresh токен
     */
    private RefreshToken validateRefreshToken(RefreshToken token) {
        Instant now = Instant.now();

        if (token.getExpiryDate().isBefore(now)) {
            refreshTokenRepository.delete(token);
            log.warn("Refresh token expired: {}", token.getToken());
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please sign in again.");
        }

        if (token.isRevoked()) {
            log.warn("Attempt to use revoked refresh token: {}", token.getToken());
            throw new RefreshTokenException(token.getToken(), "Refresh token was revoked.");
        }

        // Обновляем время последнего использования
        token.setLastUsedAt(now);
        return refreshTokenRepository.save(token);
    }

    /**
     * Генерирует безопасный токен
     */
    private String generateSecureToken() {
        // Используем UUID v4 с дополнительной энтропией
        return UUID.randomUUID().toString() +
                "-" +
                Long.toHexString(System.nanoTime()) +
                "-" +
                Integer.toHexString(hashCode());
    }

    /**
     * Рассчитывает дату истечения
     */
    private Instant calculateExpiryDate(Instant from) {
        return from.plus(refreshTokenExpiration);
    }

    /**
     * Проверяет, действителен ли токен
     */
    private boolean isTokenStillValid(RefreshToken token, Instant now) {
        return token.getExpiryDate().isAfter(now) && !token.isRevoked();
    }

    /**
     * Определяет, нужно ли обновить токен досрочно
     */
    private boolean shouldRenewEarly(Duration remainingDuration) {
        return remainingDuration.compareTo(EARLY_REFRESH_THRESHOLD) <= 0;
    }

    /**
     * Форматирует Duration для логов
     */
    private String formatDuration(Duration duration) {
        if (duration.toDays() > 0) {
            return duration.toDays() + " days";
        } else if (duration.toHours() > 0) {
            return duration.toHours() + " hours";
        } else {
            return duration.toMinutes() + " minutes";
        }
    }
}