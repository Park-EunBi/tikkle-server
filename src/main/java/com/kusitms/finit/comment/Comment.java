package com.kusitms.finit.comment;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.Certification;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import com.kusitms.finit.retrospect.Retrospect;
import com.kusitms.finit.retrospect.dto.RetrospectReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    private String createDate;
    private String  content;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;


    public void setCreateDate(LocalDateTime localDateTime) {
        String year = String.valueOf(localDateTime.getYear());
        String month = String.valueOf(localDateTime.getMonthValue());
        String day = String.valueOf(localDateTime.getDayOfMonth());
        if(day.length() == 1) {
            String prefix = "0";
            day = prefix + day;
        }
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        this.createDate = year + "년 " + month + "월 " + day + "일";
    }

}
