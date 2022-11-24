package com.kusitms.finit.certification;

import com.kusitms.finit.certification.dto.CertificationReq;
import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.certification.dto.GetFeedCertification;
import com.kusitms.finit.configure.response.CommonResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;
    private final ResponseService responseService;

    @GetMapping("/certification/{certification_id}")
    public DataResponse<GetCertificationRes> getCertificationById(@PathVariable("certification_id") Long certification_id) {
        GetCertificationRes res = certificationService.getCertificationById(certification_id);
        return responseService.getDataResponse(res);
    }

    @PostMapping("/certification/{participation_id}")
    public CommonResponse postCertificationByParticipationIdAndAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("participation_id") Long id, @RequestPart("dto")CertificationReq dto, @RequestPart(value = "images", required = false) List<MultipartFile> file) {
        certificationService.postCertificationByParticipationIdAndAccountId(customUserDetails, id, dto, file);
        return responseService.getSuccessResponse();
    }

    @GetMapping("/certification/feed/{challenge_id}")
    public DataResponse<List<GetFeedCertification>> getFeedCertificationById(@PathVariable("challenge_id") Long challenge_id) {
        List<GetFeedCertification> res = certificationService.getFeedCertificationById(challenge_id);
        return responseService.getDataResponse(res);
    }

    @GetMapping("/certification/like/{challenge_id}")
    public DataResponse<List<GetFeedCertification>> getFeedCertificationByLike(@PathVariable("challenge_id") Long challenge_id) {
        List<GetFeedCertification> res = certificationService.getFeedCertificationByLike(challenge_id);
        return responseService.getDataResponse(res);
    }
}