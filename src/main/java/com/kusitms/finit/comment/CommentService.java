package com.kusitms.finit.comment;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.ChallengeDao;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.comment.dto.GetCommentRes;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final CommentDao commentDao;

    public List<GetCommentRes> getComment(Long certification_id) {
        List<GetCommentRes> getCommentResList = commentDao.selectComment(certification_id);
        return getCommentResList;
    }
}
