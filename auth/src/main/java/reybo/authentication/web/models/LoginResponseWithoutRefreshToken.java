package reybo.authentication.web.models;

import lombok.Data;

@Data
public class LoginResponseWithoutRefreshToken {

    private final String accessToken;
    private final String firstName;
}
