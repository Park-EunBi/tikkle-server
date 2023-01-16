package com.kusitms.finit.comment;

import com.kusitms.finit.account.AccountRepository;
import com.kusitms.finit.account.entity.Account;
import com.kusitms.finit.challenge.ChallengeDao;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.challengeDetail.ChallengeDetailRepository;
import com.kusitms.finit.comment.dto.GetCommentRes;
import com.kusitms.finit.comment.dto.PostCommentReq;
import com.kusitms.finit.configure.response.exception.CustomException;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import com.kusitms.finit.configure.security.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final CommentDao commentDao;
    private Comment comment;

    public List<GetCommentRes> getComment(Long certification_id) {
        List<GetCommentRes> getCommentResList = commentDao.selectComment(certification_id);
        return getCommentResList;
    }

    public void insertComment(CustomUserDetails customUserDetails, PostCommentReq postCommentReq) {
        Account account = accountRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.ACCOUNT_NOT_FOUND));

        LocalDateTime localDateTime =  LocalDateTime.now();

        String year = String.valueOf(localDateTime.getYear());
        String month = String.valueOf(localDateTime.getMonthValue());
        String day = String.valueOf(localDateTime.getDayOfMonth());
        if(day.length() == 1) {
            String prefix = "0";
            day = prefix + day;
        }
        String createDate = year + "년 " + month + "월 " + day + "일";

        commentDao.insertComment(postCommentReq.getContent(), createDate, account.getId(), postCommentReq.getCertification_id());
        commentDao.addCommentNum(postCommentReq.getCertification_id());
    }
}
