package com.kusitms.finit.participation;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final AccountRepository accountRepository;
    private final ChallengeDetailRepository challengeDetailRepository;

    // 세부 챌린지 참여
    @Transactional
    public void participateByChallengeId(CustomUserDetails customUserDetails, Long id) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        ChallengeDetail challengeDetail = challengeDetailRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.DETAIL_CHALLENGE_NOT_FOUND));

        int num = challengeDetail.getParticipate();
        challengeDetailRepository.updateParticipate(num+1, challengeDetail.getId());

        Participation participation = Participation.createParticipation(account, challengeDetail);
        participationRepository.save(participation);
    }

    public List<String> getChallengeDetailByAccountId(CustomUserDetails customUserDetails) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));
        return participationRepository.findTitleByAccountIdAndCertificate(account.getId());
    }
}
