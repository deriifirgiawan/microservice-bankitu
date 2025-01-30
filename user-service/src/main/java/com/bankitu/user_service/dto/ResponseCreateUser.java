package com.bankitu.user_service.dto;

import com.bankitu.user_service.entity.user.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateUser {
    private String id;
    private String fullname;
    private String email;
    private String pin;
    private String created_at;
    private String updated_at;
    private ResponseCreateUserDetail user_detail;
}
