package reybo.account.dto;

import java.util.UUID;

public record AccountStatus(
        UUID id,
        boolean isDeleted,
        boolean isBlocked,
        boolean isNotFound
) {}