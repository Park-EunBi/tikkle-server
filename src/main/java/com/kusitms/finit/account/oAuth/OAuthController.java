package com.kusitms.finit.account.oAuth;

import com.kusitms.finit.account.dto.LoginResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OAuthController {

    private final OAuthService oAuthService;
    private final ResponseService responseService;

    @GetMapping("/login/oauth/kakao")
    public DataResponse<LoginResponse> login(@RequestParam String accessToken) {
        LoginResponse loginResponse = oAuthService.loginWithToken(accessToken);
        return responseService.getDataResponse(loginResponse);
    }



}
