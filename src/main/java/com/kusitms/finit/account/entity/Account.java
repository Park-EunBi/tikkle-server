package com.kusitms.finit.account.entity;

import com.kusitms.finit.account.entity.enumtypes.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String nickname;

    private String email;

    private String profileImage;

    private String password;

    private String oAuthId;

}
