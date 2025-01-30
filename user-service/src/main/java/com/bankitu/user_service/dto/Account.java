package com.bankitu.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String userId;
    private String accountNumber;
    private String cvv;
    private String saldo;
    private String validDate;

}
