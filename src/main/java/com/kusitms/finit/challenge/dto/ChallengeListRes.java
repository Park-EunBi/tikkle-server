package com.kusitms.finit.challenge.dto;

import com.kusitms.finit.challenge.Challenge;
import lombok.Data;

@Data
public class ChallengeListRes {
    private Long id;
    private String category;

    public ChallengeListRes(Challenge challenge) {
        this.id = challenge.getChallengeId();
        this.category = challenge.getCategory();
    }
}
