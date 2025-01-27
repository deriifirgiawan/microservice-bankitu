package com.bankitu.account_service.libs.card_generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CardGenerator {
    public String generateCardNumber() {
        String bin = "123456";
        int length = 16;

        StringBuilder cardNumber = new StringBuilder(bin);

        Random random = new Random();
        while (cardNumber.length() < length - 1) {
            cardNumber.append(random.nextInt(10));
        }

        int checksum = calculateLuhnChecksum(cardNumber.toString());
        cardNumber.append(checksum);

        return cardNumber.toString();
    }

    private int calculateLuhnChecksum(String number) {
        int sum = 0;
        boolean isSecondDigit = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (isSecondDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            isSecondDigit = !isSecondDigit;
        }

        return (10 - (sum % 10)) % 10;
    }
}
