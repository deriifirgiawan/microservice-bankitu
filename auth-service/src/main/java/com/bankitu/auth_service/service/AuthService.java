package com.bankitu.auth_service.service;

import com.bankitu.auth_service.dto.LoginRequest;
import com.bankitu.auth_service.dto.LoginResponse;
import com.bankitu.auth_service.dto.RegisterRequest;
import com.bankitu.auth_service.dto.User;
import com.bankitu.auth_service.feign.UserServiceClient;
import com.bankitu.auth_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validationUtil;
    private final UserServiceClient userServiceClient;

    public LoginResponse login(LoginRequest payload) {
        User validateUser = this.userServiceClient.validateUser(payload.getEmail());
        if (validateUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Not Found");
        }
        boolean isValid = this.passwordEncoder.matches(payload.getPin(), validateUser.getPin());

        if (!isValid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Pin");
        }

        String jwtToken = this.jwtService.generateToken(validateUser.getEmail());

        LoginResponse response = new LoginResponse();

        response.setToken(jwtToken);
        return response;
    }

    public String register(RegisterRequest payload) {
        this.validationUtil.validate(payload);
        User validateUser = this.userServiceClient.validateUser(payload.getEmail());

        if (validateUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Already Registered");
        }

        payload.setPin(this.passwordEncoder.encode(payload.getPin()));
        this.userServiceClient.register(payload);

        return "Success Register";
    }
}
