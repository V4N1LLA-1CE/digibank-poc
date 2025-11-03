package com.digibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "Accounts", description = "Schema to hold account information")
public record AccountDto(
    @Schema(description = "Account number of an account", example = "1023444235")
        @NotNull(message = "account number cannot be null")
        @Positive(message = "account number must be a positive number")
        Long accountNumber,
    @Schema(description = "Account type of an account", example = "Savings")
        @NotEmpty(message = "account type cannot be null or empty")
        String accountType,
    @Schema(description = "Branch address of this account", example = "123NewYork")
        @NotEmpty(message = "branch address cannot be null or empty")
        String branchAddress) {}
