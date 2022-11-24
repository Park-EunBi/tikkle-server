package com.kusitms.finit.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusitms.finit.account.dto.LoginRequest;
import com.kusitms.finit.certification.Certification;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import com.kusitms.finit.comment.Comment;
import com.kusitms.finit.heart.Heart;
import com.kusitms.finit.participation.Participation;
import com.kusitms.finit.retrospect.Retrospect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

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

    //연관관계
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Retrospect> retrospectList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Certification> certificationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChallengeDetail> challengeDetailList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Participation> participationList = new ArrayList<>();

    // 좋아요 토글
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

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
