package com.kusitms.finit.account.entity;

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

    private String nickname;

    private String email;

    private String name;

    private String ageRange;

    //private String profileImage;

    private String password;

    private String oAuthId;


    public static Account createAccount(String oAuthId, String email, String password, String nickname, String name, String ageRange) {
        return Account.builder()
                .oAuthId(oAuthId)
                .email(email)
                .name(name)
                .ageRange(ageRange)
                .password(password)
                .nickname(nickname)
                .role(RoleType.ROLE_USER)
                .build();
    }

}
