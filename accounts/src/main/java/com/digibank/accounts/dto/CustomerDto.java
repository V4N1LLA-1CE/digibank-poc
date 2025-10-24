package com.digibank.accounts.dto;

public record CustomerDto(String fullname, String email, String mobileNumber, AccountDto account) {}
