package com.digibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EnvironmentInfo", description = "Schema to hold environment information")
public record EnvironmentInfoDto(
    @Schema(description = "Environment name (dev, staging, prod)") String name,
    @Schema(description = "Environment description") String description,
    @Schema(description = "Build version") String version) {}
