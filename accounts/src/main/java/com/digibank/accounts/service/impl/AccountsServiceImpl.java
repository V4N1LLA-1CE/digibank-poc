package com.digibank.accounts.service.impl;

import com.digibank.accounts.constants.AccountsConstants;
import com.digibank.accounts.dto.CustomerDto;
import com.digibank.accounts.entity.Accounts;
import com.digibank.accounts.entity.Customer;
import com.digibank.accounts.mapper.CustomerMapper;
import com.digibank.accounts.repository.AccountsRepository;
import com.digibank.accounts.repository.CustomerRepository;
import com.digibank.accounts.service.IAccountsService;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private final CustomerRepository customerRepository;
  private final AccountsRepository accountsRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    // turn into customer entity and create in database
    Customer customer = CustomerMapper.mapToCustomer(customerDto);
    Customer savedCustomer = customerRepository.save(customer);

    // create a new account for customer that has just been saved
    accountsRepository.save(buildNewAccount(savedCustomer));
  }

  /**
   * Builds a new Accounts entity with a randomly generated account number. Creates a default
   * Savings account linked to the provided customer.
   *
   * @param customer - Customer entity to associate with the new account
   * @return Accounts entity with generated account number and default settings
   */
  private Accounts buildNewAccount(Customer customer) {
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
    return Accounts.builder()
        .customerId(customer.getCustomerId())
        .accountNumber(randomAccNumber)
        .accountType(AccountsConstants.SAVINGS)
        .branchAddress(AccountsConstants.ADDRESS)
        .build();
  }
}
