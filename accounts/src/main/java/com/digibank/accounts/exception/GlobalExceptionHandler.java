package com.digibank.accounts.exception;

import com.digibank.accounts.dto.ErrorResponseDto;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerAlreadyExistsException.class)
  public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
      CustomerAlreadyExistsException exception, WebRequest webRequest) {
    ErrorResponseDto errorResponseDto =
        new ErrorResponseDto(
            webRequest.getDescription(false),
            HttpStatus.CONFLICT,
            exception.getMessage(),
            LocalDateTime.now());

    return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {
    ErrorResponseDto errorResponseDto =
        new ErrorResponseDto(
            webRequest.getDescription(false),
            HttpStatus.NOT_FOUND,
            exception.getMessage(),
            LocalDateTime.now());

    return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
  }
}
