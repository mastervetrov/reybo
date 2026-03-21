package reybo.authentication.services;

import reybo.authentication.events.ChangedEmailEvent;
import reybo.authentication.events.ChangedPasswordEvent;
import reybo.authentication.web.models.*;

public interface SecurityService {

    void register(CreateUserRequest createUserRequest, String secret);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void logout();

    LoginResponse authenticateUser(LoginRequest loginRequest);

    Boolean validateToken(String token);

    Response changePassword(ChangedPasswordEvent event);

    Response changeEmail(ChangedEmailEvent event);

    Response recoveryPassword(RecoveryPassportRequest request);
}
