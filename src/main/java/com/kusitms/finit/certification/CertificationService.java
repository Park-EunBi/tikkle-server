package com.kusitms.finit.certification;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.dto.CertificationReq;
import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.certification.dto.GetFeedCertification;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.s3.S3Uploader;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.participation.Participation;
import com.kusitms.finit.participation.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final CertificationDao certificationDao;
    private final AccountRepository accountRepository;
    private final ParticipationRepository participationRepository;
    private final ChallengeDetailRepository challengeDetailRepository;
    private final S3Uploader s3Uploader;

    public GetCertificationRes getCertificationById(Long certification_id) {
        GetCertificationRes getCertificationRes = certificationDao.selectCertification(certification_id);
        return getCertificationRes;
    }

    @Transactional
    public void postCertificationByParticipationIdAndAccountId(CustomUserDetails customUserDetails, Long id, CertificationReq dto, List<MultipartFile> file) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        Participation participation = participationRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.PARTICIPATION_NOT_FOUND));

        ChallengeDetail challengeDetail = challengeDetailRepository.findById(participation.getChallengeDetail().getId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.DETAIL_CHALLENGE_NOT_FOUND));

        Certification certification = null;
        if (file != null) {
            List<String> nameList = s3Uploader.uploadImage(file);
            StringBuilder urlList = new StringBuilder();
            for (String name : nameList) {
                urlList.append("https://kusitms26thimagebucket.s3.ap-northeast-2.amazonaws.com/");
                urlList.append(name);
                urlList.append("#");
            }
            certification = Certification.createCertification(account, challengeDetail, dto, urlList.toString());
        }
        else {
            certification = Certification.createCertification(account, challengeDetail, dto, null);
        }
        certificationRepository.save(certification);
        participationRepository.updateCertificate(participation.getId());
    }

    public List<GetFeedCertification> getFeedCertificationById(Long challenge_id) {
        List<GetFeedCertification> getFeedCertification = certificationDao.selectFeedCertification(challenge_id);
        return getFeedCertification;

    }

    public List<GetFeedCertification> getFeedCertificationByLike(Long challenge_id) {
        List<GetFeedCertification> getFeedCertification = certificationDao.selectFeedCertificationByLike(challenge_id);
        return getFeedCertification;
    }
}