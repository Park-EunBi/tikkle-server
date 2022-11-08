package com.kusitms.finit.account.oAuth;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.dto.LoginResponse;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OAuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse loginWithToken(String token) {
        Account account = validateSignInByToken(token);
        String accessToken = jwtTokenProvider.createToken(account.getEmail(),account.getRole());
        return new LoginResponse(account, accessToken);
    }

    private KakaoUserInfo getUserAttributesByToken(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        JSONObject body = new JSONObject(response.getBody());
        System.out.println(body);
        Long id = body.getLong("id");
        String email = body.getJSONObject("kakao_account").getString("email");
        String nickname = body.getJSONObject("properties").getString("nickname");
        //String name = body.getJSONObject("kakao_account").getString("name");
        String ageRange = body.getJSONObject("kakao_account").getString("age_range");

        if(id == null || email == null || nickname == null || ageRange == null)
            throw new CustomException(CustomExceptionStatus.OAUTH_EMPTY_INFORM);

        return new KakaoUserInfo(id, nickname, email, ageRange);
    }

    private Account validateSignInByToken(String token) {
        //accessToken으로 userProfile정보 얻기
        KakaoUserInfo kakaoUserInfo = getUserAttributesByToken(token);

        String kakao_id = "kakao_" + kakaoUserInfo.getId();

        String email = kakaoUserInfo.getEmail();
        String nickname = kakaoUserInfo.getNickname();
        //String name = kakaoUserInfo.getName();
        String ageRange = kakaoUserInfo.getAgeRange();

        Account account = null;
        //중복회원 확인
        Optional<Account> byKakaoId = accountRepository.findByoAuthId(kakao_id);
        if(byKakaoId.isPresent()) account = byKakaoId.get();
        else {
            Account newAccount = Account.createAccount(kakao_id, email, passwordEncoder.encode(kakao_id), nickname, "name", ageRange);
            Account save = accountRepository.save(newAccount);
            account = save;
        }
        return account;
    }
}
