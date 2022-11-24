package com.kusitms.finit.heart;

import com.kusitms.finit.comment.CommentService;
import com.kusitms.finit.comment.dto.GetCommentRes;
import com.kusitms.finit.comment.dto.PostCommentReq;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;
    private final ResponseService responseService;

    @PostMapping("/heart/{certification_id}")
    public DataResponse<String> getRetrospectByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable(value = "certification_id") Long certification_id) {

        System.out.println("certification_id" + certification_id);
        if(heartService.getHeart(customUserDetails, certification_id) == 1) {
            String res = "좋아요가 등록되었습니다.";
            return responseService.getDataResponse(res);
        }
        else {
            String res = "좋아요를 취소했습니다.";
            return responseService.getDataResponse(res);
        }


    }
}
