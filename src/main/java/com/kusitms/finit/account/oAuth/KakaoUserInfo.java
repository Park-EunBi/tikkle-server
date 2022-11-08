package com.kusitms.finit.account.oAuth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    Long id;
    String nickname;
    String email;
    String ageRange;
}
