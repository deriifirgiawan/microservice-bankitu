package com.bankitu.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    private Long id;
    private String user_id;
    private String address;
    private String phone_number;
    private String nik;
    private String mother_name;
}
