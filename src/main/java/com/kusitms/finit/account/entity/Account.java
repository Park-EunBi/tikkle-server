package com.kusitms.finit.account.entity;

import com.kusitms.finit.account.dto.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String kakaoNickName;

    private String email;

    private String ageRange;

    private String password;

    private String oAuthId;

    //추가 정보
    private String nickname;

    private String intro;

    private int profileImage;

    private int monthlyIncome;

    private int savingCost;

    private int fixedCost;



    public static Account createAccount(String oAuthId, String email, String password, String kakaoNickName, String ageRange) {
        return Account.builder()
                .oAuthId(oAuthId)
                .email(email)
                .ageRange(ageRange)
                .password(password)
                .kakaoNickName(kakaoNickName)
                .role(RoleType.ROLE_USER)
                .build();
    }

}
