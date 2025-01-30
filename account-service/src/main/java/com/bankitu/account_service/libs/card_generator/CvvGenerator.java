package com.bankitu.account_service.libs.card_generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CvvGenerator {
    public String generateCvv() {
        Random random = new Random();
        StringBuilder cvv = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }

        return cvv.toString();
    }
}
