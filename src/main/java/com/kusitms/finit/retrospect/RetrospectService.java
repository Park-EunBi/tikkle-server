package com.kusitms.finit.retrospect;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.retrospect.dto.RetrospectReq;
import com.kusitms.finit.retrospect.dto.RetrospectRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetrospectService {

    private final RetrospectRepository retrospectRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void createRetrospectByAccountId(CustomUserDetails customUserDetails, RetrospectReq dto) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        Retrospect retrospect = Retrospect.createRetrospect(account, dto);
        retrospectRepository.save(retrospect);
    }

    public RetrospectRes getRetrospectByAccountId(CustomUserDetails customUserDetails, String date) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        Retrospect retrospect = retrospectRepository.findByAccountIdAndCreateDate(account.getId(), date)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.RETROSPECT_NOT_FOUND));

        return new RetrospectRes(retrospect.getId(), retrospect.getCreateDate(), retrospect.getImageIndex(), retrospect.getContent(), retrospect.getHashTag());

    }
}
