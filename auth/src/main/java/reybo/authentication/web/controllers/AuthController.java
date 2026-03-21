package reybo.authentication.web.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reybo.authentication.events.ChangedEmailEvent;
import reybo.authentication.events.ChangedPasswordEvent;
import reybo.authentication.repositories.UserRepository;
import reybo.authentication.services.CaptchaService;
import reybo.authentication.services.SecurityService;
import reybo.authentication.web.models.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;

    private final SecurityService securityService;
    private final CaptchaService captchaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public LoginResponse registerUser(@RequestBody CreateUserRequest request,
                                 HttpSession session,
                                 HttpServletResponse response) {

        String captchaSecret = (String) session.getAttribute("captcha");
        securityService.register(request, captchaSecret);

        LoginResponse loginResponse = securityService.authenticateUser(LoginRequest.builder()
                .email(request.getEmail())
                .password(request.getPassword1()).build());

        Cookie refreshToken = new Cookie("REFRESH_TOKEN", loginResponse.getRefreshToken());
        refreshToken.setHttpOnly(true);
        refreshToken.setPath("/");
        refreshToken.setMaxAge(1209600);
        response.addCookie(refreshToken);

        return loginResponse;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response
    ) {
        if (refreshToken == null) {
            throw new RuntimeException("Refresh token not found");
        }

        RefreshTokenResponse tokenResponse = securityService.refreshToken(new RefreshTokenRequest(refreshToken));

        // Устанавливаем новый refreshToken в куку
        Cookie newRefreshToken = new Cookie("REFRESH_TOKEN", tokenResponse.getRefreshToken());
        newRefreshToken.setHttpOnly(true);
        newRefreshToken.setSecure(true);
        newRefreshToken.setPath("/");
        newRefreshToken.setMaxAge(7 * 24 * 60 * 60); // 7 дней
        response.addCookie(newRefreshToken);

        // Возвращаем только новый accessToken
        return ResponseEntity.ok(RefreshTokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .build());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/password/recovery/")
    public Response recoveryPassword(@RequestBody RecoveryPassportRequest request) {
        return securityService.recoveryPassword(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public Response logoutUser(HttpServletResponse response) {
        securityService.logout();

        String[] cookieNames = {"REFRESH_TOKEN"};

        for (String cookieName : cookieNames) {
            Cookie cookie = new Cookie(cookieName, null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new Response("Logged out successfully");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse authUser(@RequestBody LoginRequest loginRequest,
                        HttpServletResponse response) {

        LoginResponse loginResponse = securityService.authenticateUser(loginRequest);

        Cookie refreshToken = new Cookie("REFRESH_TOKEN", loginResponse.getRefreshToken());
        refreshToken.setHttpOnly(true);
        refreshToken.setPath("/");
        refreshToken.setMaxAge(1209600);
        response.addCookie(refreshToken);

        return loginResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/change-password-link")
    public Response changePassword(ChangedPasswordEvent event) {
        return securityService.changePassword(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/change-email-link")
    public Response changeEmail(ChangedEmailEvent event) {
        return securityService.changeEmail(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/validate")
    public Boolean validateToken(@RequestParam String token) {
        return securityService.validateToken(token);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/captcha")
    public String getCaptcha(HttpSession session) {
        CaptchaDto captchaDto = captchaService.generateCaptcha();

        session.setAttribute("captcha", captchaDto.getSecret());

        session.setMaxInactiveInterval(300); // 5 минут

        return captchaDto.getImage();
    }
}