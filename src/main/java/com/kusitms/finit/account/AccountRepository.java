package com.kusitms.finit.account;

import com.kusitms.finit.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByoAuthId(String oAuthId);
    Optional<Account> findByEmail(String email);
}
