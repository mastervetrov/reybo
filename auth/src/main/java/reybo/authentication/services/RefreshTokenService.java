package reybo.authentication.services;

import reybo.authentication.entities.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {

    Optional<RefreshToken> findByRefreshToken(String token);

    RefreshToken createRefreshToken(UUID userId);

    RefreshToken checkRefreshToken(String token);

    void deleteByUserId(UUID userId);

    // Новые методы
    void deleteRefreshToken(String token);

    void rotateRefreshToken(String oldToken, UUID userId);

    boolean isRefreshTokenValid(String token);

    long countActiveTokens();
}