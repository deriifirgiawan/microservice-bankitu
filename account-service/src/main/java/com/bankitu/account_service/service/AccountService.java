package com.bankitu.account_service.service;

import com.bankitu.account_service.dto.CreateAccount;
import com.bankitu.account_service.entity.Account;
import com.bankitu.account_service.libs.card_generator.CardGenerator;
import com.bankitu.account_service.libs.card_generator.CvvGenerator;
import com.bankitu.account_service.repository.AccountRepository;
import com.bankitu.account_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ValidationUtil validationUtil;
    private final CardGenerator cardGenerator;
    private final CvvGenerator cvvGenerator;

    public Account createAccount(CreateAccount payload) {
        validationUtil.validate(payload);

        var existingAccount = this.accountRepository.findAccountByUserId(payload.getUser_id());

        if (existingAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user_id already registered");
        }

        Account account = new Account();
        account.setSaldo(payload.getSaldo());
        account.setAccountNumber(cardGenerator.generateCardNumber());
        account.setCvv(cvvGenerator.generateCvv());
        account.setUserId(payload.getUser_id());
        account.setValidDate("");

        return this.accountRepository.save(account);

    }
}
