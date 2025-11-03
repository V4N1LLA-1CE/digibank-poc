package com.digibank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info =
        @Info(
            title = "Digibank PoC - Accounts microservice",
            description = "Documentation for Accounts microservice REST API",
            version = "v1",
            contact = @Contact(name = "Austin Sofaer", email = "austinsofaer@gmail.com"),
            license = @License(name = "Apache 2.0")),
    externalDocs =
        @ExternalDocumentation(
            description = "Digibank PoC Accounts microservice REST API Documentation"))
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }
}
