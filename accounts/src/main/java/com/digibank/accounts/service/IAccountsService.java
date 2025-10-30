package com.digibank.accounts.service;

import com.digibank.accounts.dto.CustomerDto;

/**
 * Service interface for managing customer accounts.
 */
public interface IAccountsService {
  /**
   * Creates a new account for a customer.
   *
   * @param customerDto the customer details
   */
  void createAccount(CustomerDto customerDto);

  /**
   * Fetches account details by mobile number.
   *
   * @param mobileNumber the customer's mobile number
   * @return the customer and account details
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   * Updates existing account information.
   *
   * @param customerDto the updated customer details
   * @return true if update was successful, false otherwise
   */
  boolean updateAccount(CustomerDto customerDto);

  /**
   * Deletes an account by mobile number.
   *
   * @param mobileNumber the customer's mobile number
   * @return true if deletion was successful, false otherwise
   */
  boolean deleteAccount(String mobileNumber);
}
