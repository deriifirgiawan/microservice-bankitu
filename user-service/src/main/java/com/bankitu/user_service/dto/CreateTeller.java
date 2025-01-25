package com.bankitu.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeller {
    @NotBlank
    private String fullname;

    @NotBlank
    private String email;

    @NotBlank
    private String pin;
}
