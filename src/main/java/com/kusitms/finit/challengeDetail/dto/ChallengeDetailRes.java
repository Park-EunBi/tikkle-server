package com.kusitms.finit.challengeDetail.dto;

import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.Data;

@Data
public class ChallengeDetailRes {
    private String title;
    private String intro;

    public ChallengeDetailRes(ChallengeDetail challengeDetail) {
        title = challengeDetail.getTitle();
        intro = challengeDetail.getIntro();
    }
}
