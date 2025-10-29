package com.digibank.accounts.mapper;

import com.digibank.accounts.dto.AccountDto;
import com.digibank.accounts.entity.Account;

public final class AccountsMapper {

  private AccountsMapper() {
    // restrict instantiation
  }

  public static AccountDto mapToAccountsDto(Account account) {
    return new AccountDto(
        account.getAccountNumber(), account.getAccountType(), account.getBranchAddress());
  }

  public static Account mapToAccounts(AccountDto accountDto, Account account) {
    account.setAccountType(accountDto.accountType());
    account.setBranchAddress(accountDto.branchAddress());
    return account;
  }
}
