package com.kusitms.finit.challengeDetail.dto;

import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.Data;

@Data
public class ChallengeDetailResultRes {
    private String writer;
    private String title;
    private String intro;
    private String target;
    private int participation;
    private int feed;

    public ChallengeDetailResultRes(ChallengeDetail challengeDetail, int feed) {
        this.writer = challengeDetail.getAccount().getNickname();
        this.title = challengeDetail.getTitle();
        this.intro = challengeDetail.getIntro();
        this.target = challengeDetail.getTarget();
        this.participation = challengeDetail.getParticipate();
        this.feed = feed;
    }
}
