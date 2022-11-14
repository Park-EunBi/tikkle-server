package com.kusitms.finit.retrospect;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.retrospect.dto.RetrospectReq;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Retrospect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retrospect_id")
    private Long id;

    private String createDate;

    private int imageIndex;

    private String content;

    private String hashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
        account.getRetrospectList().add(this);
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

    public static Retrospect createRetrospect(Account account, RetrospectReq dto) {
        Retrospect retrospect = new Retrospect();
        retrospect.setCreateDate(LocalDateTime.now());
        retrospect.setImageIndex(dto.getImageIndex());
        retrospect.setContent(dto.getContent());
        retrospect.setAccount(account);
        retrospect.setHashTag(dto.getHashTag());
        return retrospect;
    }
}
