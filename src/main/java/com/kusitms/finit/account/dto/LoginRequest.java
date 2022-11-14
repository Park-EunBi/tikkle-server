package com.kusitms.finit.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {
    private String nickname;
    private String intro;
    private int profileImage;
    private int monthlyIncome;
    private int savingCost;
    private int fixedCost;

    public LoginRequest(String nickname, String intro, int profileImage, int monthlyIncome, int savingCost, int fixedCost) {
        this.nickname = nickname;
        this.intro = intro;
        this.profileImage = profileImage;
        this.monthlyIncome = monthlyIncome;
        this.savingCost = savingCost;
        this.fixedCost = fixedCost;
    }
}
