package com.kusitms.finit.participation;

import com.kusitms.finit.configure.response.CommonResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;
    private final ResponseService responseService;

    // 세부 챌린지 참여
    @PostMapping("/participate/{challengeDetailId}")
    public CommonResponse participateByChallengeId(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                   @PathVariable(value = "challengeDetailId") Long id) {
        participationService.participateByChallengeId(customUserDetails, id);
        return responseService.getSuccessResponse();
    }

    // 참여중인 세부 챌린지 조회
    @GetMapping("/participate/challengeDetail")
    public DataResponse<List<String>> getChallengeDetailByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<String> list = participationService.getChallengeDetailByAccountId(customUserDetails);
        return responseService.getDataResponse(list);
    }
}
