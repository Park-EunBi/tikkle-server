package com.kusitms.finit.account.oAuth;

import com.kusitms.finit.account.dto.LoginResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//https://github.com/wupitch/wupitch-server/blob/main/src/main/java/com/server/wupitch/account/oAuth/OAuthController.java
public class OAuthController {

    private final OAuthService oAuthService;
    private final ResponseService responseService;

    @GetMapping("/login/oauth/kakao")
    public DataResponse<LoginResponse> login(@RequestParam String accessToken) {
        LoginResponse loginResponse = oAuthService.loginWithToken(accessToken);
        return responseService.getDataResponse(loginResponse);
    }

}
