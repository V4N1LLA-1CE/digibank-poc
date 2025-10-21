package com.digibank.accounts.mapper;

import com.digibank.accounts.dto.AccountsDto;
import com.digibank.accounts.entity.Accounts;

public final class AccountsMapper {

  private AccountsMapper() {
    // restrict instantiation
  }

  public static AccountsDto mapToAccountsDto(Accounts accounts) {
    return new AccountsDto(
        accounts.getAccountNumber(), accounts.getAccountType(), accounts.getBranchAddress());
  }

  public static Accounts mapToAccounts(AccountsDto accountsDto) {
    return Accounts.builder()
        .accountNumber(accountsDto.accountNumber())
        .accountType(accountsDto.accountType())
        .branchAddress(accountsDto.branchAddress())
        .build();
  }
}
