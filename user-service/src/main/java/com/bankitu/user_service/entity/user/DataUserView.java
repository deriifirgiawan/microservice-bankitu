package com.bankitu.user_service.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "data_users")
public class DataUserView {
    @Id
    private String userId;

    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String motherName;
    private Timestamp createdAt;
}
