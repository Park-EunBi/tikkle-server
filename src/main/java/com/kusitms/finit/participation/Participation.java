package com.kusitms.finit.participation;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participation_id")
    private Long id;

    private boolean certificate;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_detail_id")
    private ChallengeDetail challengeDetail;

    //
    public void setAccount(Account account) {
        this.account = account;
        account.getParticipationList().add(this);
    }

    public void setChallengeDetail(ChallengeDetail challengeDetail) {
        this.challengeDetail = challengeDetail;
        challengeDetail.getParticipationList().add(this);
    }

    // 생성 메서드
    public static Participation createParticipation(Account account, ChallengeDetail challengeDetail) {
        Participation participation = new Participation();
        participation.setAccount(account);
        participation.setChallengeDetail(challengeDetail);
        participation.setCertificate(false);
        return participation;
    }

}
