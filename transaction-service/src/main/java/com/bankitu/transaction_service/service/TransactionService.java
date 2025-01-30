package com.bankitu.transaction_service.service;

import com.bankitu.transaction_service.dto.CreateTransaction;
import com.bankitu.transaction_service.entity.Transaction;
import com.bankitu.transaction_service.entity.TransactionStatusType;
import com.bankitu.transaction_service.libs.ReferenceIDGenerator;
import com.bankitu.transaction_service.repository.TransactionRepository;
import com.bankitu.transaction_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ValidationUtil validationUtil;
    private final ReferenceIDGenerator referenceIDGenerator;

    public Transaction createTransaction(CreateTransaction payload) {
        this.validationUtil.validate(payload);
        // Find User Id

        // Create Transaction
        Transaction transaction = new Transaction();
        transaction.setUserId(payload.getUser_id());
        transaction.setDescription(payload.getDescription());
        transaction.setSourceAccount(payload.getSource_account());
        transaction.setDestinationAccount(payload.getDestination_account());
        transaction.setReferenceId(this.referenceIDGenerator.generate());
        transaction.setTransactionType(payload.getTransaction_type());
        transaction.setStatus(TransactionStatusType.PENDING);

        return this.transactionRepository.save(transaction);
    }
}
