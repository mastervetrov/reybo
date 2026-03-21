package reybo.authentication.web.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RecoveryPassportRequest {
    private String email;
}