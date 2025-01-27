package com.bankitu.account_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "saldo", nullable = false)
    private String saldo;

    @Column(name = "valid_date", nullable = false)
    private String validDate;
}
