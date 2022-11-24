package com.kusitms.finit.challengeDetail.dto;

import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.Data;

@Data
public class ChallengeDetailRes {
    private Long id;
    private String title;
    private String intro;
    private int participate;

    public ChallengeDetailRes(ChallengeDetail challengeDetail) {
        id = challengeDetail.getId();
        title = challengeDetail.getTitle();
        intro = challengeDetail.getIntro();
        participate = challengeDetail.getParticipate();
    }
}
