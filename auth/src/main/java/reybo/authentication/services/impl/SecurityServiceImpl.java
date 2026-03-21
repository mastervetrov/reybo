package reybo.authentication.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reybo.authentication.entities.PasswordResetToken;
import reybo.authentication.entities.RefreshToken;
import reybo.authentication.entities.user.RoleType;
import reybo.authentication.entities.user.User;
import reybo.authentication.events.ChangedEmailEvent;
import reybo.authentication.events.ChangedPasswordEvent;
import reybo.authentication.events.NewUserRegisteredEvent;
import reybo.authentication.exceptions.AlreadyExitsException;
import reybo.authentication.exceptions.EntityNotFoundException;
import reybo.authentication.exceptions.RefreshTokenException;
import reybo.authentication.mappers.UserMapper;
import reybo.authentication.repositories.UserRepository;
import reybo.authentication.security.AppUserDetails;
import reybo.authentication.security.jwt.JwtUtils;
import reybo.authentication.services.CaptchaService;
import reybo.authentication.services.PasswordResetTokenService;
import reybo.authentication.services.RefreshTokenService;
import reybo.authentication.services.SecurityService;
import reybo.authentication.web.models.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    private final CaptchaService captchaService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordResetTokenService passwordResetTokenService;

    private final UserRepository userRepository;
    private final KafkaTemplate<String, NewUserRegisteredEvent> kafkaTemplate;

    private final UserMapper userMapper;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${app.kafka.kafkaUserTopic}")
    private String topicName;

    @Transactional
    @Override
    public void register(CreateUserRequest request, String secret) {

        System.out.println("МЕТОД ВЫЗВАН" + request.toString());
        if (userRepository.existsByEmail(request.getEmail())) {
            System.out.println("Не валидный email");
            throw new AlreadyExitsException("Email already exists");
        }
        if (!request.getPassword1().equals(request.getPassword2())) {
            System.out.println("Не валидный пароль");
            throw new RuntimeException("Passwords do not match");
        }
        if (!captchaService.validateCaptcha(secret, request.getCaptchaCode())) {
            System.out.println("Не валидная капча");
            throw new RuntimeException("Invalid captcha");
        }
        System.out.println("Проверки пройдены");

        var user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .passwordHash(passwordEncoder.encode(request.getPassword2()))
                .build();

        user.setRoles(new HashSet<>(Set.of(RoleType.ROLE_USER)));
        userRepository.save(user);
        Optional<User> newUser = userRepository.findByEmail(request.getEmail());
        kafkaTemplate.send(topicName, userMapper.userToNewUserRegisteredEvent(newUser.get(), request));
    }

    @Transactional
    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByRefreshToken(requestRefreshToken)
                .map(token -> refreshTokenService.checkRefreshToken(token.getToken()))
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    User tokenOwner = userRepository.findById(userId).orElseThrow(() ->
                            new RefreshTokenException("Exception trying to get token for userId: " + userId));
                    String token = jwtUtils.generateTokenFromUser(tokenOwner);
                    return new RefreshTokenResponse(token, refreshTokenService.createRefreshToken(userId).getToken());
                }).orElseThrow(() -> new RefreshTokenException(requestRefreshToken, "Refresh token not found"));
    }

    @Override
    public void logout() {
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (currentPrincipal instanceof AppUserDetails userDetails) {
            UUID userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
        }
        SecurityContextHolder.clearContext();
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return LoginResponse.builder()
                .accessToken(jwtUtils.generateJwtToken(userDetails))
                .firstName(userDetails.getFirstName())
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public Boolean validateToken(String token) {
        return jwtUtils.validate(token);
    }

    @Transactional
    @Override
    public Response changePassword(ChangedPasswordEvent event) {
        User user = userRepository.findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setPasswordHash(passwordEncoder.encode(event.getNewPassword()));
        userRepository.save(user);
        return new Response("Password changed successfully");
    }

    @Transactional
    @Override
    public Response changeEmail(ChangedEmailEvent event) {
        User user = userRepository.findById(event.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(event.getNewEmail());
        userRepository.save(user);
        return new Response("Email changed successfully");
    }

    @Override
    public Response recoveryPassword(RecoveryPassportRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User with such email not found"));
        PasswordResetToken resetToken = passwordResetTokenService.createToken(user.getId());
        String resetUrl = String.format("https://185.129.146.54:8765/api/v1/auth/password/reset?token=%s", resetToken.getToken());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(email);
        message.setSubject("Смена пароля");

        // todo переделать message
        String text = String.format(
                "Привет!\n\nВы запросили восстановление пароля.\n" +
                        "Кликните по ссылке для замены пароля:\n%s\n\n" +
                        "Если вы не запрашивали восстановление — проигнорируйте письмо.",
                resetUrl);
        message.setText(text);
        mailSender.send(message);
        return new Response("Email with password has been sent to your email. Please check your inbox.");
    }
}