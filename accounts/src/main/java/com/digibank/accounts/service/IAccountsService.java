package com.digibank.accounts.service;

import com.digibank.accounts.dto.CustomerDto;

public interface IAccountsService {
  /**
   * @param customerDto - Customer Dto object
   */
  void createAccount(CustomerDto customerDto);

  /**
   * @param mobileNumber - String of customer's mobile number
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   * @param customerDto - Customer Dto object with updated information
   */
  boolean updateAccount(CustomerDto customerDto);
}
