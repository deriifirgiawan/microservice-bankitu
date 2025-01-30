package com.bankitu.auth_service.service;

import com.bankitu.auth_service.dto.RegisterRequest;
import com.bankitu.auth_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users/validate?email={email}")
    User validateUser(@PathVariable("email") String email);

    @PostMapping("/users/create")
    User register(@RequestBody RegisterRequest payload);
}
