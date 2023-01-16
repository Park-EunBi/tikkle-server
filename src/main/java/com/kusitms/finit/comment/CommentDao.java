package com.kusitms.finit.comment;

import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.comment.dto.GetCommentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCommentRes> selectComment (long certification_id) {
        String selectCommentQuery = "select nickname, create_date, content, count(*) as commentNum\n" +
                "    from account, comment\n" +
                "    where account.account_id = comment.account_id\n" +
                "    and certification_id = ?";
        long selectCommentParam = certification_id;

        return this.jdbcTemplate.query(selectCommentQuery,
                (rs, rowNum) -> new GetCommentRes(
                        rs.getString("nickname"),
                        rs.getString("create_date"),
                        rs.getString("content"),
                        rs.getInt("commentNum")),
                selectCommentParam);
    }

    public void insertComment(String content, String create_date, Long account_id, Long certification_id) {
        String insertCommentQuery = "insert into comment (content,  create_date, account_id, certification_id) VALUES (?, ?, ?, ?)";
        Object[] insertCommentParams = new Object[]{content, create_date, account_id, certification_id};
        this.jdbcTemplate.update(insertCommentQuery, insertCommentParams);
    }

    public void addCommentNum(long certification_id) {
        String addCommentNumQuery = "update certification set comment_num = comment_num+ 1 where certification_id = ?;";
        long addCommentNumParam = certification_id;
        this.jdbcTemplate.update(addCommentNumQuery, addCommentNumParam);
    }
}

