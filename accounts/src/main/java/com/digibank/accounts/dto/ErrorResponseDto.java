package com.digibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public record ErrorResponseDto(
    @Schema(description = "API path invoked by client") String apiPath,
    @Schema(description = "Error code representing the error that happened") HttpStatus errorCode,
    @Schema(description = "Error message representing the error that happened") String errorMsg,
    @Schema(description = "Timestamp representing when the error happened")
        LocalDateTime timestamp) {}
