package reybo.authentication.services;

import reybo.authentication.web.models.CaptchaDto;

public interface CaptchaService {

    CaptchaDto generateCaptcha();

    boolean validateCaptcha(String secret, String answer);
}