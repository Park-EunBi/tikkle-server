package com.kusitms.finit.heart;


import com.kusitms.finit.account.entity.Account;

import com.kusitms.finit.certification.Certification;
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
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="heart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}