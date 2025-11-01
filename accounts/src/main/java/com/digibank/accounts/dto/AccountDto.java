package com.digibank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AccountDto(
    @NotNull(message = "account number cannot be null")
        @Positive(message = "account number must be a positive number")
        Long accountNumber,
    @NotEmpty(message = "account type cannot be null or empty") String accountType,
    @NotEmpty(message = "branch address cannot be null or empty") String branchAddress) {}
