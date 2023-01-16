package com.kusitms.finit.certification;

import com.kusitms.finit.certification.dto.GetCertificationRes;
import com.kusitms.finit.certification.dto.GetFeedCertification;
import com.kusitms.finit.challenge.dto.ChallengeImgRes;
import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CertificationDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetCertificationRes selectCertification(long certificationId ) {
        String selectCertificationQuery = "select category, certification.title, account.nickname, certification_date, certification_image, content, likes\n" +
                "from account, certification, challenge_detail, challenge\n" +
                "where account.account_id = certification.account_id\n" +
                "  and certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "  and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and certification_id = ?; ";
        long selectCertificationParam = certificationId;

        return this.jdbcTemplate.queryForObject(selectCertificationQuery,
                (rs, rowNum) -> new GetCertificationRes(
                        rs.getString("category"),
                        rs.getString("title"),
                        rs.getString("nickname"),
                        rs.getDate("certification_date"),
                        rs.getString("certification_image"),
                        rs.getString("content"),
                        rs.getInt("likes")),
                selectCertificationParam);

    }


    public List<GetFeedCertification> selectFeedCertification(Long challenge_id) {

        String selectFeedCertificationQuery = "select certification_image, certification.title, content, comment_num, heart_num\n" +
                "from certification, challenge_detail, challenge\n" +
                "where certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and challenge.challenge_id = ?";
        long selectCertificationParam = challenge_id;

        return this.jdbcTemplate.query(selectFeedCertificationQuery,
                (rs, rowNum) -> new GetFeedCertification(
                        rs.getString("certification_image"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("comment_num"),
                        rs.getInt("heart_num")),
                selectCertificationParam);
    }

    public List<GetFeedCertification> selectFeedCertificationByLike(Long challenge_id) {
        String selectFeedCertificationByLikeQuery = "select certification_image, certification.title, content, comment_num, heart_num\n" +
                "from certification, challenge_detail, challenge\n" +
                "where certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and challenge.challenge_id = ?\n" +
                "order by likes desc;";
        long selectFeedCertificationByLikeParam = challenge_id;

        return this.jdbcTemplate.query(selectFeedCertificationByLikeQuery,
                (rs, rowNum) -> new GetFeedCertification(
                        rs.getString("certification_image"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("comment_num"),
                        rs.getInt("heart_num")),
                selectFeedCertificationByLikeParam);
    }

    public List<GetFeedCertification> selectFeedSearchCertification(Long challenge_id, String search_word) {
        String selectFeedSearchCertificationQuery = "select certification_image, certification.title, content, comment_num, heart_num\n" +
                "from certification, challenge_detail, challenge\n" +
                "where certification.challenge_detail_id = challenge_detail.challenge_detail_id\n" +
                "and challenge_detail.challenge_id = challenge.challenge_id\n" +
                "and challenge.challenge_id = ?\n" +
                "and (certification.title like ? or content like ?)\n" +
                "order by likes desc;";

        String search_word_replace= search_word.replace("\"", "");
        search_word_replace = "%" + search_word_replace +"%";

        Object[] selectFeedSearchCertificationParam = new Object[]{challenge_id, search_word_replace, search_word_replace};

        return this.jdbcTemplate.query(selectFeedSearchCertificationQuery,
                (rs, rowNum) -> new GetFeedCertification(
                        rs.getString("certification_image"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("comment_num"),
                        rs.getInt("heart_num")),
                selectFeedSearchCertificationParam);
    }
}