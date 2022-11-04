package com.kusitms.finit.configure.response.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    NOT_AUTHENTICATED_ACCOUNT(false, 2004, "로그인이 필요합니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
