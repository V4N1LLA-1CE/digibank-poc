package com.digibank.accounts.repository;

import com.digibank.accounts.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByCustomerId(Long id);
}
