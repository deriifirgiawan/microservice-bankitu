package com.bankitu.api_gateway;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    private String secretKey;

    @PostConstruct
    public void init() {
        secretKey = environment.getProperty("jwt.secret");
    }

    public String getSecretKey() {
        return secretKey;
    }
}
