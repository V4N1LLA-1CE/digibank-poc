package com.digibank.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "environment")
public record EnvironmentConfig(String name, String description) {}
