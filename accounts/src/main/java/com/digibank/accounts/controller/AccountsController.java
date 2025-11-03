package com.digibank.accounts.controller;

import com.digibank.accounts.constants.AccountsConstants;
import com.digibank.accounts.dto.CustomerDto;
import com.digibank.accounts.dto.ErrorResponseDto;
import com.digibank.accounts.dto.ResponseDto;
import com.digibank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "CRUD REST API",
    description =
        "Documentation for CRUD operations for Accounts to CREATE, UPDATE, FETCH and DELETE account"
            + " details")
@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

  private final IAccountsService iAccountsService;

  @Operation(
      summary = "Endpoint to create an account",
      description = "This endpoint creates a new Customer and Account")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "HTTP Status Created"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Status Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PostMapping("")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

    iAccountsService.createAccount(customerDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Endpoint to fetch an account",
      description = "This endpoint fetches Account and Customer baseed on mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Status Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("")
  public ResponseEntity<CustomerDto> fetchAccountDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
          String mobileNumber) {
    CustomerDto customerWithAccountsDto = iAccountsService.fetchAccount(mobileNumber);

    return ResponseEntity.status(HttpStatus.OK).body(customerWithAccountsDto);
  }

  @Operation(
      summary = "Endpoint to update account and customer details",
      description = "This endpoint allows update to Account and Customer details")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
    @ApiResponse(responseCode = "417", description = "Expectation Failed"),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Status Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PutMapping("")
  public ResponseEntity<ResponseDto> updateAccountDetails(
      @Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = iAccountsService.updateAccount(customerDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
  }

  @Operation(
      summary = "Endpoint to delete account and customer details",
      description = "This endpoint deletes Customer and Account based on mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
    @ApiResponse(responseCode = "417", description = "Expectation Failed"),
    @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
  })
  @DeleteMapping("")
  public ResponseEntity<ResponseDto> deleteAccountDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be 10 digits")
          String mobileNumber) {
    boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
  }
}
