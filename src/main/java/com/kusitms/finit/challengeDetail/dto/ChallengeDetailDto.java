package com.kusitms.finit.challengeDetail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDetailDto {
    private String title;
    private String intro;
    private String target;
}
