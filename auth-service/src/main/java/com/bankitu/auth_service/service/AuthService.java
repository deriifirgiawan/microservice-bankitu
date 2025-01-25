package com.bankitu.auth_service.service;

import com.bankitu.auth_service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login() {
        String password = this.passwordEncoder.encode("adminpassword");
        User user = new User("admin@bankitu.com", password, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        String jwtToken = this.jwtService.generateToken(user);

        LoginResponse response = new LoginResponse();

        response.setToken(jwtToken);

        return response;
    }
}
