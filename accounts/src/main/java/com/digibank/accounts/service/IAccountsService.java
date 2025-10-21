package com.digibank.accounts.service;

import com.digibank.accounts.dto.CustomerDto;

public interface IAccountsService {
  /**
   * @param customerDto - Customer Dto object
   */
  void createAccount(CustomerDto customerDto);
}
