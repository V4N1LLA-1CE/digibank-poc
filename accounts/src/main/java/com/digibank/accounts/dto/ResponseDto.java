package com.digibank.accounts.dto;

import com.digibank.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Response", description = "Schema to hold successful response information")
public record ResponseDto(
    @Schema(description = "Status code in the response", example = AccountsConstants.STATUS_200)
        String statusCode,
    @Schema(description = "Status message in the response", example = AccountsConstants.MESSAGE_200)
        String statusMsg) {}
