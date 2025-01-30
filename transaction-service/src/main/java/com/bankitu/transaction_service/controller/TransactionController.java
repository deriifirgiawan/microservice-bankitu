package com.bankitu.transaction_service.controller;

import com.bankitu.transaction_service.dto.CreateTransaction;
import com.bankitu.transaction_service.entity.Transaction;
import com.bankitu.transaction_service.response.BaseResponse;
import com.bankitu.transaction_service.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public BaseResponse<Transaction> createTransaction(@RequestBody CreateTransaction payload) {
        BaseResponse<Transaction> response = new BaseResponse<>();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("Success Create Transaction");
        response.setData(this.transactionService.createTransaction(payload));

        return response;
    }
}
