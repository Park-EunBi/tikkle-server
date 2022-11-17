package com.kusitms.finit.account;

import com.kusitms.finit.account.dto.LoginRequest;
import com.kusitms.finit.account.dto.LoginResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final ResponseService responseService;

    @PostMapping("/login/extraInfo/{id}")
    public DataResponse<LoginResponse> login(@PathVariable(value = "id") Long id, @RequestBody LoginRequest request) {
        LoginResponse loginResponse = accountService.setExtraInfo(id, request);
        return responseService.getDataResponse(loginResponse);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "cicd test";

    }
}
