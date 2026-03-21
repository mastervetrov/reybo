package reybo.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${feign.clients.auth-service.name}",
        url = "${feign.clients.auth-service.url}")
public interface AuthClient {

    @GetMapping("/api/v1/auth/captcha")
    CaptchaDto getCaptcha();

    @GetMapping("/api/v1/auth/validate")
    Boolean validateToken(@RequestParam String token);

}
