package com.digibank.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
public record BuildConfig(String version) {}
