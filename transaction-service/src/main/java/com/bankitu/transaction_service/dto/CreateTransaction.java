package com.bankitu.transaction_service.dto;

import com.bankitu.transaction_service.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransaction {
    @NotBlank
    private String user_id;

    @NotBlank
    private String source_account;

    @NotBlank
    private String destination_account;

    @NotBlank
    private String amount;

    @NotBlank
    private TransactionType transaction_type;

    private String description;
}
