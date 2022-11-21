package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.Challenge;
import com.kusitms.finit.challenge.ChallengeRepository;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailDto;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.participation.Participation;
import com.kusitms.finit.participation.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeDetailService {

    private final ChallengeDetailRepository challengeDetailRepository;
    private final AccountRepository accountRepository;
    private final ParticipationRepository participationRepository;
    private final ChallengeRepository challengeRepository;

    // 챌린지 개설
    @Transactional
    public Long postChallengeDetail(CustomUserDetails customUserDetails, Long id, ChallengeDetailDto dto) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.CHALLENGE_NOT_FOUND));

        ChallengeDetail challengeDetail = ChallengeDetail.createChallengeDetail(account, challenge, dto);
        ChallengeDetail save = challengeDetailRepository.save(challengeDetail);
        return save.getId();
    }

    // 참여 중인 세부 챌린지 조회
    public void getChallengeDetailByAccountId(CustomUserDetails customUserDetails) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        List<String> list = participationRepository.findByAccountId(account.getId());


    }


}
