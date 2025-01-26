package com.bankitu.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {
    @NotBlank
    private String fullname;

    @NotBlank
    private String email;

    @NotBlank
    private String pin;

    @NotBlank
    private String address;

    @NotBlank
    private String phone_number;

    @NotBlank
    private String nik;

    @NotBlank
    private String mother_name;
}
