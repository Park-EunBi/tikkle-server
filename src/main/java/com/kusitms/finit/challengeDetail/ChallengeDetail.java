package com.kusitms.finit.challengeDetail;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.Challenge;
import com.kusitms.finit.challengeDetail.dto.ChallengeDetailDto;
import com.kusitms.finit.participation.Participation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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

    private String target;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @JsonIgnore
    @OneToMany(mappedBy = "challengeDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participationList = new ArrayList<>();

    //
    public void setAccount(Account account) {
        this.account = account;
        account.getChallengeDetailList().add(this);
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
        challenge.getChallengeDetailList().add(this);
    }


    public static ChallengeDetail createChallengeDetail(Account account, Challenge challenge, ChallengeDetailDto dto) {
        ChallengeDetail challengeDetail = new ChallengeDetail();
        challengeDetail.setTitle(dto.getTitle());
        challengeDetail.setIntro(dto.getIntro());
        challengeDetail.setTarget(dto.getTarget());
        challengeDetail.setAccount(account);
        challengeDetail.setChallenge(challenge);
        return challengeDetail;
    }

}
