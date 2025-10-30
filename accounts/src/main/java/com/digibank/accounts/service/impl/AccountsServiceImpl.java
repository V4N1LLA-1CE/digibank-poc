package com.digibank.accounts.service.impl;

import com.digibank.accounts.constants.AccountsConstants;
import com.digibank.accounts.dto.AccountDto;
import com.digibank.accounts.dto.CustomerDto;
import com.digibank.accounts.entity.Account;
import com.digibank.accounts.entity.Customer;
import com.digibank.accounts.exception.CustomerAlreadyExistsException;
import com.digibank.accounts.exception.ResourceNotFoundException;
import com.digibank.accounts.mapper.AccountsMapper;
import com.digibank.accounts.mapper.CustomerMapper;
import com.digibank.accounts.repository.AccountsRepository;
import com.digibank.accounts.repository.CustomerRepository;
import com.digibank.accounts.service.IAccountsService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private final CustomerRepository customerRepository;
  private final AccountsRepository accountsRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    // check if there is existing user using the same mobile number in the database
    Optional<Customer> customer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
    if (customer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobile number: " + customerDto.mobileNumber());
    }

    // create customer entity and save in database
    Customer customerToSave =
        Customer.builder()
            .fullname(customerDto.fullname())
            .email(customerDto.email())
            .mobileNumber(customerDto.mobileNumber())
            .build();

    customerToSave.setCreatedAt(LocalDateTime.now());
    customerToSave.setCreatedBy("Anonymous");
    Customer savedCustomer = customerRepository.save(customerToSave);

    // create a new account for customer that has just been saved
    accountsRepository.save(buildNewAccount(savedCustomer));
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Account account =
        accountsRepository
            .findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "customerId", customer.getCustomerId().toString()));

    return CustomerMapper.mapToCustomerDto(customer, account);
  }

  @Override
  @Transactional
  public boolean updateAccount(CustomerDto customerDto) {
    AccountDto accountDto = customerDto.account();
    if (accountDto == null) {
      return false;
    }

    // fetch account and customer
    // throw exception if not found on either
    Account account =
        accountsRepository
            .findById(accountDto.accountNumber())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "accountNumber", accountDto.accountNumber().toString()));

    Customer customer =
        customerRepository
            .findById(account.getCustomerId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Customer", "customerId", account.getCustomerId().toString()));

    // update entities using JPA dirty checking
    AccountsMapper.mapToAccounts(accountDto, account);
    CustomerMapper.mapToCustomer(customerDto, customer);

    return true;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Account", "mobileNumber", mobileNumber.toString()));
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }

  /**
   * Builds a new Accounts entity with a randomly generated account number. Creates a default
   * Savings account linked to the provided customer.
   *
   * @param customer - Customer entity to associate with the new account
   * @return Accounts entity with generated account number and default settings
   */
  private Account buildNewAccount(Customer customer) {
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
    Account newAccount =
        Account.builder()
            .customerId(customer.getCustomerId())
            .accountNumber(randomAccNumber)
            .accountType(AccountsConstants.SAVINGS)
            .branchAddress(AccountsConstants.ADDRESS)
            .build();

    newAccount.setCreatedAt(LocalDateTime.now());
    newAccount.setCreatedBy("Anonymous");

    return newAccount;
  }
}
