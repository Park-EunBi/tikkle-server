package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.challengeDetail.dto.ChallengeDetailDto;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChallengeDetailController {

    private final ChallengeDetailService challengeDetailService;
    private final ResponseService responseService;

    // 세부 챌린지 개설
    @PostMapping("/challengeDetail/{challengeId}")
    public DataResponse<Long> postChallengeDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @PathVariable(value = "challengeId") Long id,
                                                  @RequestBody ChallengeDetailDto dto) {
        Long challengeDetailId = challengeDetailService.postChallengeDetail(customUserDetails, id, dto);
        return responseService.getDataResponse(challengeDetailId);
    }

    /*
    // 세부 챌린지 전체 조회 (최근 업로드 순)
    @GetMapping("/challengeDetail/{challengeId}")
    public void getChallengeDetailByLatest() {
        challengeDetailService.getChallengeDetailByLatest();
    }

     */

    // 세부 챌린지 신청
   // @PostMapping("/challengeDetail/{challengeDetailId}")

    //참여 중인 세부 챌린지 조회
    @GetMapping("/challengeDetail")
    public void getChallengeDetailByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        challengeDetailService.getChallengeDetailByAccountId(customUserDetails);
    }
}
