package com.kusitms.finit.configure.response.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    NOT_AUTHENTICATED_ACCOUNT(false, 2004, "로그인이 필요합니다."),
    OAUTH_EMPTY_INFORM(false, 2560, "OAuth에 필요한 정보가 누락되었습니다."),

    //user
    ACCOUNT_NOT_FOUND(false, 2011, "사용자를 찾을 수 없습니다."),

    //retrospect
    RETROSPECT_NOT_FOUND(false, 2020, "회고를 찾을 수 없습니다."),

    //challenge
    CHALLENGE_NOT_FOUND(false, 2030, "챌린지를 찾을 수 없습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
