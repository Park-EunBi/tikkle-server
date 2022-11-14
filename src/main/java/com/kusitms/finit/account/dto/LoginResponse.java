package com.kusitms.finit.account.dto;

import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.account.entity.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String responseType;
    private Long id;
    private RoleType role;
    private String accessToken;

    public LoginResponse(String responseType, Account account, String accessToken){
        this.responseType = responseType;
        this.id = account.getAccountId();
        this.role = account.getRole();
        this.accessToken = accessToken;
    }
}