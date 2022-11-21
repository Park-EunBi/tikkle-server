package com.kusitms.finit.comment;

import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import com.kusitms.finit.comment.dto.GetCommentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}

/*    select nickname, create_date, content
    from account, comment
    where account.account_id = comment.account_id
    and certification_id = ?;

 */

