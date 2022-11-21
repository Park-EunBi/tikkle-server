package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.challengeDetail.dto.ChallengeDetailDto;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailRes;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 세부 챌린지 전체 조회 (최근 업로드 순)
    @GetMapping("/challengeDetail/{challengeId}/latest")
    public DataResponse<List<ChallengeDetailRes>> getChallengeDetailByLatest(@PathVariable(value = "challengeId") Long id) {
        List<ChallengeDetailRes> list = challengeDetailService.getChallengeDetailByLatest(id);
        return responseService.getDataResponse(list);
    }

    // 세부 챌린지 전체 조회 (참여 많은 순)
    @GetMapping("/challengeDetail/{challengeId}/participate")
    public DataResponse<List<ChallengeDetailRes>> getChallengeDetailByParticipation(@PathVariable(value = "challengeId") Long id) {
        List<ChallengeDetailRes> list = challengeDetailService.getChallengeDetailByParticipation(id);
        return responseService.getDataResponse(list);
    }

    // 세부 챌린지 상세 조회
    @GetMapping("challengeDetail/{challengeDetailId}")
    public void getChallengeDetail(@PathVariable(value = "challengeId") Long id) {
        challengeDetailService.getChallengeDetail(id);
    }

    //참여 중인 세부 챌린지 조회
    @GetMapping("/challengeDetail")
    public void getChallengeDetailByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        challengeDetailService.getChallengeDetailByAccountId(customUserDetails);
    }
}
