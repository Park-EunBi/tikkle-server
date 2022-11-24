package com.kusitms.finit.heart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class HeartDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int checkHeart(Long id, Long certification_id) {
        String checkHeartQuery = "select exists (select * from heart where account_id = ? and certification_id = ?)";

        Object[] checkHeartParams = new Object[]{id, certification_id};
        return this.jdbcTemplate.queryForObject(checkHeartQuery,
                int.class,
                checkHeartParams);
    }

    public void deleteHeart(Long id, Long certification_id) {
        String deleteHeartQuery = "DELETE FROM heart\n" +
                "WHERE account_id = ? and certification_id = ?;";
        Object[] deleteHeartParams = new Object[]{id, certification_id};

        this.jdbcTemplate.update(deleteHeartQuery, deleteHeartParams);
    }

    public void insertHeart(Long id, Long certification_id) {
        String insertHeartQuery = "insert into heart (account_id, certification_id)\n" +
                "values (?,?);";
        Object[] insertHeartParams = new Object[]{id, certification_id};

        this.jdbcTemplate.update(insertHeartQuery, insertHeartParams);
    }
}
