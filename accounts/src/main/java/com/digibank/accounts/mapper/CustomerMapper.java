package com.digibank.accounts.mapper;

import com.digibank.accounts.dto.AccountDto;
import com.digibank.accounts.dto.CustomerDto;
import com.digibank.accounts.entity.Account;
import com.digibank.accounts.entity.Customer;

public final class CustomerMapper {

  private CustomerMapper() {
    // restrict instantiation
  }

  public static Customer mapToCustomer(CustomerDto customerDto) {
    return Customer.builder()
        .fullname(customerDto.fullname())
        .email(customerDto.email())
        .mobileNumber(customerDto.mobileNumber())
        .build();
  }

  public static CustomerDto mapToCustomerDto(Customer customer) {
    return mapToCustomerDto(customer, null);
  }

  public static CustomerDto mapToCustomerDto(Customer customer, Account account) {
    AccountDto accountDto = null;
    if (account != null) {
      accountDto = AccountsMapper.mapToAccountsDto(account);
    }

    return new CustomerDto(
        customer.getFullname(), customer.getEmail(), customer.getMobileNumber(), accountDto);
  }
}
