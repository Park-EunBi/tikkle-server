package com.kusitms.finit.heart;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class HeartService {
    private final AccountRepository accountRepository;
    private final HeartDao heartDao;

    public int getHeart(CustomUserDetails customUserDetails, Long certification_id) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        if (heartDao.checkHeart(account.getId(), certification_id) == 1) {
            heartDao.deleteHeart(account.getId(), certification_id);
            heartDao.subHeartNum(certification_id);
            return 0;
        }
        else {
            heartDao.insertHeart(account.getId(), certification_id);
            heartDao.addHeartNum(certification_id);
            return 1;
        }
    }
}
