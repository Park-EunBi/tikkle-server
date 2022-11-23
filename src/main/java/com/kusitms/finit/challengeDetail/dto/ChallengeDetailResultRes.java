package com.kusitms.finit.challengeDetail.dto;

import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.Data;

@Data
public class ChallengeDetailResultRes {
    private String title;
    private String intro;
    private String target;
    private int participation;
    private int feed;

    public ChallengeDetailResultRes(ChallengeDetail challengeDetail, int feed) {
        title = challengeDetail.getTitle();
        intro = challengeDetail.getIntro();
        target = challengeDetail.getTarget();
        participation = challengeDetail.getParticipate();
        feed = feed;
    }
}
