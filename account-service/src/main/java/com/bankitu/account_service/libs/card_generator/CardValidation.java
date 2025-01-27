package com.bankitu.account_service.libs.card_generator;

import org.springframework.stereotype.Component;

@Component
public class CardValidation {
    public boolean isValidCard(String card) {
        int sum = 0;
        boolean isSecondDigit = false;

        for (int i = card.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(card.charAt(i));

            if (isSecondDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            isSecondDigit = !isSecondDigit;
        }

        return (sum % 10) == 0;
    }
}
