package reybo.shop.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtClaims {
    // Standard JWT claims (RFC 7519)
    private String iss;              // Issuer
    private UUID sub;              // Subject (userId или email)
    private String aud;              // Audience
    private Date exp;               // Expiration time
    private Date nbf;               // Not Before
    private Date iat;               // Issued At
    private String jti;             // JWT ID

    // Custom claims
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private String tokenType;       // "ACCESS" или "REFRESH"

    // Optional claims
    private String phone;
    private boolean emailVerified;
    private Map<String, Object> additionalClaims;
}