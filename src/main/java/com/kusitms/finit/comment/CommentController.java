package com.kusitms.finit.comment;

import com.kusitms.finit.challenge.ChallengeService;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.comment.dto.GetCommentRes;
import com.kusitms.finit.comment.dto.PostCommentReq;
import com.kusitms.finit.configure.response.DataResponse;
import com.kusitms.finit.configure.response.ResponseService;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import com.kusitms.finit.retrospect.dto.RetrospectReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;

    @GetMapping("/comment/{certification_id}")
    public DataResponse<List<GetCommentRes>> getRetrospectByAccountId(@PathVariable("certification_id") Long certification_id) {
        List<GetCommentRes> res = commentService.getComment(certification_id);
        return responseService.getDataResponse(res);
    }

    @PostMapping("/comment")
    public DataResponse<String> getRetrospectByAccountId(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostCommentReq postCommentReq) {
        commentService.insertComment(customUserDetails, postCommentReq);
        String res = "댓글이 등록되었습니다.";
        return responseService.getDataResponse(res);
    }

}


