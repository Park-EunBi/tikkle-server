package com.kusitms.finit.configure.security.authentication;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByEmail(email);
        if(optional.isEmpty()) return null;
        else return new CustomUserDetails(optional.get());
    }
}
