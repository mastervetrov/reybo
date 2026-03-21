package reybo.shop.common.security.advice;

import reybo.shop.common.security.dto.SecurityMessage;
import reybo.shop.common.security.exception.AuthServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class AuthServiceControllerAdvice {

    @ExceptionHandler(AuthServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public SecurityMessage authServiceNotFoundExceptionHandler(AuthServiceNotFoundException e) {
        return new SecurityMessage("Не удалось установить соединение с сервисом аутентификации");
    }
}
