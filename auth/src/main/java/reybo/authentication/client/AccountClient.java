//package reybo.authentication.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import reybo.authentication.web.models.CaptchaDto;
//
//@FeignClient(
//        name = "${feign.clients.account-service.name}",
//        url = "${feign.clients.account-service.url}")
//public interface AccountClient {
//
//    @GetMapping("/api/v1/auth/captcha")
//    CaptchaDto getCaptcha();
//
//    @GetMapping("/api/v1/auth/validate")
//    Boolean validateToken(@RequestParam String token);
//
//}
