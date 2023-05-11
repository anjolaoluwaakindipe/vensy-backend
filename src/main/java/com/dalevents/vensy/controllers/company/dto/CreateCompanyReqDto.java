package com.dalevents.vensy.controllers.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCompanyReqDto(@NotNull @NotBlank String name, @NotNull @NotBlank String phoneNumber,
        @NotNull @NotEmpty @Email String email, @NotNull String address) {

}
