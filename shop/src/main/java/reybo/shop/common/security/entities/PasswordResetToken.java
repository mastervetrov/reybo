package reybo.shop.common.security.entities;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class PasswordResetToken {

    private UUID id;

    private UUID userId;

    private String token;

    private Instant expiryDate;
}