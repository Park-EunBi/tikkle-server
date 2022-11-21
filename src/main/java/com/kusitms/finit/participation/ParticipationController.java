package com.kusitms.finit.participation;

import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    // 세부 챌린지 참여
    @PostMapping("/participate/{challengeDetailId}")
    public void participateByChallengeId(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @PathVariable(value = "challengeDetailId") Long id) {
        participationService.participateByChallengeId(customUserDetails, id);
    }
}
