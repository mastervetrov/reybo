package reybo.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "${feign.clients.account-service.name}",
        url = "${feign.clients.account-service.url}")
public interface AccountClient {

    @GetMapping("/api/v1/account/me")
    AccountDto getCurrentAccount();

}