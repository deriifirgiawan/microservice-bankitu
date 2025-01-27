package com.bankitu.account_service.controllers;

import com.bankitu.account_service.dto.CreateAccount;
import com.bankitu.account_service.entity.Account;
import com.bankitu.account_service.response.BaseResponse;
import com.bankitu.account_service.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public BaseResponse<Account> createAccount(@RequestBody CreateAccount payload) {
        BaseResponse<Account> response = new BaseResponse<>();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("Success Create Account");
        response.setData(this.accountService.createAccount(payload));

        return response;
    }

}
