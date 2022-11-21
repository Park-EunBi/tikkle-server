package com.kusitms.finit.challenge;

import com.kusitms.finit.challenge.dto.ChallengeCertificationRes;
import com.kusitms.finit.challenge.dto.ChallengeListRes;
import com.kusitms.finit.challenge.dto.MyChallengeListRes;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
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
public class ChallengeController {
    private final ChallengeService challengeService;
    private final ResponseService responseService;

    @GetMapping("/challenge")
    public DataResponse<List<TodayChallengeRes>> getRetrospectByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<TodayChallengeRes> res = challengeService.getTodayChallengeByAccountId(customUserDetails);
        return responseService.getDataResponse(res);
    }

    @GetMapping("/challenge/today/num")
    public DataResponse<String> getTodayChallengeNum(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String res = challengeService.getTodayChallengeNum(customUserDetails);
        return responseService.getDataResponse(res);
    }

    @GetMapping("challenge/list/{challenge_id}")
    public DataResponse<MyChallengeListRes> getChallengeList(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable ("challenge_id") Long challenge_id) {
        MyChallengeListRes res = challengeService.getChallengeList(customUserDetails, challenge_id);
        return responseService.getDataResponse(res);
    }

    // 챌린지 조회
    @GetMapping("/challenge/list")
    public DataResponse<List<ChallengeListRes>> getDefaultChallengeList() {
        List<ChallengeListRes> list = challengeService.getDefaultChallengeList();
        return responseService.getDataResponse(list);
    }

    // 챌린지 인증
    @PostMapping("/challenge/{challenge_id}")
    public void setCertification(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                 @PathVariable("challenge_id") Long challengeId,
                                 @RequestPart(value = "certificationDto") ChallengeCertificationRes dto,
                                 @RequestPart(value = "image", required = false) MultipartFile file) {
        challengeService.setCertification(customUserDetails, challengeId, dto, file);
    }
}
