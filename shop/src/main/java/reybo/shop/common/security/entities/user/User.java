package reybo.shop.common.security.entities.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class User {

    private UUID id;

    private String email;

    private AccountStatus accountStatus;

    private int failedLoginAttempts;

    private LocalDateTime lockoutUntil;

    private String refreshTokenHash;

    private boolean mfaEnabled;

    private String passwordHash;

    private Set<RoleType> roles;
}