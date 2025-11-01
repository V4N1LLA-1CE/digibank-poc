package com.digibank.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(
    @NotEmpty(message = "fullname cannot be null or empty")
        @Size(min = 5, max = 50, message = "fullname should between 5 to 50 characters")
        String fullname,
    @NotEmpty(message = "email cannot be null or empty")
        @Email(message = "email must be a valid format")
        String email,
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
        String mobileNumber,
    @Valid AccountDto account) {}
