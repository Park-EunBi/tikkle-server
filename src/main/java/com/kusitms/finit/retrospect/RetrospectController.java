package com.kusitms.finit.retrospect;

import com.kusitms.finit.configure.response.CommonResponse;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.retrospect.dto.RetrospectReq;
import com.kusitms.finit.retrospect.dto.RetrospectRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RetrospectController {

    private final RetrospectService retrospectService;
    private final ResponseService responseService;

    @PostMapping("/retrospect")
    public CommonResponse createRetrospectByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                      @RequestBody RetrospectReq dto) {
        retrospectService.createRetrospectByAccountId(customUserDetails, dto);
        return responseService.getSuccessResponse();
    }

    @GetMapping("/retrospect")
    public DataResponse<RetrospectRes> getRetrospectByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                @RequestParam String date) {
        RetrospectRes res = retrospectService.getRetrospectByAccountId(customUserDetails, date);
        return responseService.getDataResponse(res);

    }

}
