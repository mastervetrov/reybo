package reybo.shop.common.security.entities;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {

    private Long id;

    private UUID userId;

    private String token;

    private Instant expiryDate;

    private Instant createdAt;

    private Instant lastUsedAt;

    private boolean revoked = false;

    private Long previousTokenId; // Для отслеживания цепочки токенов

    private Long version;

}