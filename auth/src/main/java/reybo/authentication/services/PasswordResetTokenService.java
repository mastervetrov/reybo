package reybo.authentication.services;

import reybo.authentication.entities.PasswordResetToken;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenService {
    PasswordResetToken createToken(UUID userId);

    Optional<PasswordResetToken> validateToken(String token);

    void deleteToken(String token);
}