package com.kusitms.finit.account;

import com.kusitms.finit.account.dto.LoginRequest;
import com.kusitms.finit.account.dto.LoginResponse;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.configure.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public LoginResponse setExtraInfo(Long id, LoginRequest request) {
        Optional<Account> byId = accountRepository.findById(id);

        Account account = null;
        if(byId.isPresent()) account = byId.get();

        accountRepository.updateExtraInfoByAccountId(id, request.getNickname(), request.getIntro(), request.getProfileImage(), request.getMonthlyIncome(), request.getSavingCost(), request.getFixedCost());
        String accessToken = jwtTokenProvider.createToken(account.getEmail(), account.getRole());

        return new LoginResponse("SignIn", account, accessToken);
    }
}
