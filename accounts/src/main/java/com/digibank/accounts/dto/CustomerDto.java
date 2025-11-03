package com.digibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public record CustomerDto(
    @Schema(description = "Fullname of the customer", example = "Michael Schumacher")
        @NotEmpty(message = "fullname cannot be null or empty")
        @Size(min = 5, max = 50, message = "fullname should between 5 to 50 characters")
        String fullname,
    @Schema(description = "Email of the customer", example = "michaelschumacher@gmail.com")
        @NotEmpty(message = "email cannot be null or empty")
        @Email(message = "email must be a valid format")
        String email,
    @Schema(description = "Mobile number of the customer", example = "0444444444")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
        String mobileNumber,
    @Schema(description = "Account details of the customer") @Valid AccountDto account) {}
