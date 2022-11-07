package com.kusitms.finit.configure.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

    private Boolean isSuccess;

    private int code;

    private String message;
}
