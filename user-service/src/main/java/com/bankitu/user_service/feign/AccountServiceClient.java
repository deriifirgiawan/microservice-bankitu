package com.bankitu.user_service.feign;

import com.bankitu.user_service.dto.CreateAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountServiceClient {
    @PostMapping("/account/create-account")
    void createAccount(@RequestBody CreateAccount payload);
}
