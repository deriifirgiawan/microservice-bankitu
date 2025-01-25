package com.bankitu.auth_service.controller;

import com.bankitu.auth_service.dto.LoginResponse;
import com.bankitu.auth_service.response.BaseResponse;
import com.bankitu.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public BaseResponse<LoginResponse> login() {
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        var token = this.authService.login();
        response.setData(token);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Success");

        return response;
    }
}
