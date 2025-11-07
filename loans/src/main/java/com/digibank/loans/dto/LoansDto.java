package com.digibank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record LoansDto(
    @NotEmpty(message = "mobile number cannot be empty")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
        @Schema(description = "Mobile number of customer", example = "0436543879")
        String mobileNumber,
    @NotEmpty(message = "loan number cannot be empty")
        @Pattern(regexp = "(^$|[0-9]{12})", message = "loan number must be 12 digits")
        @Schema(description = "Loan number of the customer", example = "343667546665")
        String loanNumber,
    @NotEmpty(message = "loan type cannot be empty")
        @Schema(description = "Type of loan", example = "Home Loan")
        String loanType,
    @Positive(message = "total loan amount should be greater than zero")
        @Schema(description = "Total loan amount")
        int totalLoan,
    @PositiveOrZero(message = "total loan amount paid should be equal or greater than zero")
        @Schema(description = "Total loan amount paid", example = "1000")
        int amountPaid,
    @PositiveOrZero(message = "total outstanding amount should be equal to or greater than zero")
        @Schema(description = "Total outstanding amount against a loan", example = "99000")
        int outstandingAmount) {}
