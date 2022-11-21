package com.kusitms.finit.challengeDetail;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.Certification;
import com.kusitms.finit.challenge.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChallengeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_detail_id")
    private Long id;

    private String title;

    private String intro;

    private int goodCount;

    private String challengeImage;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @JsonIgnore
    @OneToMany(mappedBy = "challengeDetail", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Certification> certificationList = new ArrayList<>();

    public void setAccount(Account account) {
        this.account = account;
        account.getChallengeDetailList().add(this);
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
        challenge.getChallengeDetailList().add(this);
    }

    //생성 메서드

}
