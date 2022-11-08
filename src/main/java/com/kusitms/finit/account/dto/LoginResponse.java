package com.kusitms.finit.account.dto;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.account.entity.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private Long id;
    private String nickName;
    private RoleType role;
    private String accessToken;

    public LoginResponse(Account account, String accessToken){
        this.id = account.getAccountId();
        this.nickName = account.getNickname();
        this.role = account.getRole();
        this.accessToken = accessToken;
    }
}