package com.bankitu.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String fullname;
    private String email;
    private String pin;
    private String created_at;
    private String updated_at;
    private UserDetail user_detail;
}
