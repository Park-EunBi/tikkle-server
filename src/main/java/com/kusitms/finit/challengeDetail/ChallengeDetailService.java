package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.CertificationRepository;
import com.kusitms.finit.challenge.Challenge;
import com.kusitms.finit.challenge.ChallengeRepository;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailDto;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailRes;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailResultRes;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.participation.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeDetailService {

    private final ChallengeDetailRepository challengeDetailRepository;
    private final AccountRepository accountRepository;
    private final ParticipationRepository participationRepository;
    private final ChallengeRepository challengeRepository;
    private final CertificationRepository certificationRepository;

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

    // 세부 챌린지 전체 조회 (최근 업로드 순)
    public List<ChallengeDetailRes> getChallengeDetailByLatest(Long id) {
        List<ChallengeDetail> list = challengeDetailRepository.findByChallengeDetailByLatest(id);
        return list.stream().map(ChallengeDetailRes::new).collect(Collectors.toList());
    }

    // 세부 챌린지 전체 조회 (좋아요 많은 순)
    public List<ChallengeDetailRes> getChallengeDetailByParticipation(Long id) {
        List<ChallengeDetail> list = challengeDetailRepository.findByChallengeDetailByParticipate(id);
        return list.stream().map(ChallengeDetailRes::new).collect(Collectors.toList());
    }

    // 세부 챌린지 상세 조회
    public ChallengeDetailResultRes getChallengeDetail(Long id) {
        ChallengeDetail challengeDetail = challengeDetailRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.DETAIL_CHALLENGE_NOT_FOUND));
        int feed = certificationRepository.findByChallengeDetailId(id).size();
        return new ChallengeDetailResultRes(challengeDetail, feed);
    }

    // 참여 중인 세부 챌린지 조회
    public void getChallengeDetailByAccountId(CustomUserDetails customUserDetails) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        List<String> list = participationRepository.findByAccountId(account.getId());


    }
}
