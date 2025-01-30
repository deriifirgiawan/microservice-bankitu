package com.bankitu.transaction_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uuid_sequence")
    @GenericGenerator(
            name = "uuid_sequence",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
            }
    )
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;


    @Column(name = "reference_id", nullable = false)
    private String referenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "transaction_date", nullable = false)
    private Timestamp transactionDate;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatusType status;

    @Column(name = "source_account")
    private String sourceAccount;

    @Column(name = "destination_account")
    private String destinationAccount;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "deleted_at")
    private Timestamp deleted_at;

    @PrePersist
    public void prePersist() {
        Timestamp now = Timestamp.from(Instant.now());
        this.transactionDate = now;
        this.updated_at = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = Timestamp.from(Instant.now());
    }
}
