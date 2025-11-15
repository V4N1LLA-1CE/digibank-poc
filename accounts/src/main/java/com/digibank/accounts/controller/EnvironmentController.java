package com.digibank.accounts.controller;

import com.digibank.accounts.config.BuildConfig;
import com.digibank.accounts.config.EnvironmentConfig;
import com.digibank.accounts.dto.EnvironmentInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Environment Info", description = "REST API to get environment information")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnvironmentController {

    private final BuildConfig buildConfig;
    private final EnvironmentConfig environmentConfig;

    @Operation(
        summary = "Get Environment Info",
        description = "Get current environment information including name, description, and version"
    )
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping("/environment")
    public ResponseEntity<EnvironmentInfoDto> getEnvironmentInfo() {
    EnvironmentInfoDto environmentInfo =
        new EnvironmentInfoDto(
            environmentConfig.name(), environmentConfig.description(), buildConfig.version());
        return ResponseEntity.status(HttpStatus.OK).body(environmentInfo);
    }
}
