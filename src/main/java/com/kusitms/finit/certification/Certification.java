package com.kusitms.finit.certification;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="certification_id")
    private Long id;

    private String title;

    private String content;

    private String certificationImage;

    private String createDate;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_detail_id")
    private ChallengeDetail challengeDetail;

    public void setCreateDate(LocalDateTime localDateTime) {
        String year = String.valueOf(localDateTime.getYear());
        String month = String.valueOf(localDateTime.getMonthValue());
        String day = String.valueOf(localDateTime.getDayOfMonth());
        if(day.length() == 1) {
            String prefix = "0";
            day = prefix + day;
        }
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        this.createDate = year + ". " + month + ". " + day + ".";
    }

}