package com.bankitu.auth_service.controller;

import com.bankitu.auth_service.dto.LoginRequest;
import com.bankitu.auth_service.dto.LoginResponse;
import com.bankitu.auth_service.dto.RegisterRequest;
import com.bankitu.auth_service.response.BaseResponse;
import com.bankitu.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest payload) {
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        var token = this.authService.login(payload);
        response.setData(token);
        response.setStatus(HttpStatus.OK);
        response.setMessage("Success");

        return response;
    }

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody RegisterRequest payload) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setData(this.authService.register(payload));
        response.setStatus(HttpStatus.OK);
        response.setMessage("Success");

        return response;
    }
}
