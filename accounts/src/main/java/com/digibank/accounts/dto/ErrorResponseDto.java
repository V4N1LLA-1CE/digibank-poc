package com.digibank.accounts.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
    String apiPath, HttpStatus errorCode, String errorMsg, LocalDateTime timestamp) {}
