package com.kusitms.finit.certification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.certification.dto.CertificationReq;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import com.kusitms.finit.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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

    private int likes;

    private String createDate;

    private int commentNum;

    private int heartNum;

    // 연관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_detail_id")
    private ChallengeDetail challengeDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    public void setAccount(Account account) {
        this.account = account;
        account.getCertificationList().add(this);
    }

    public void setChallengeDetail(ChallengeDetail challengeDetail) {
        this.challengeDetail = challengeDetail;
        challengeDetail.getCertificationList().add(this);
    }

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

    //생성메서드
    public static Certification createCertification(Account account, ChallengeDetail challengeDetail, CertificationReq dto, String url){
        Certification certification = new Certification();
        certification.setAccount(account);
        certification.setChallengeDetail(challengeDetail);
        certification.setTitle(dto.getTitle());
        certification.setContent(dto.getContent());
        certification.setCertificationImage(url);
        certification.setLikes(0);
        certification.setCreateDate(LocalDateTime.now());
        return certification;
    }
}