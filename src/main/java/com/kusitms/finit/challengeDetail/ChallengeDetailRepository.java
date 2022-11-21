package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeDetailRepository extends JpaRepository<ChallengeDetail, Long> {

    @Query(value = "select cd.title, cd.intro, cd.challenge_image " +
            "from challenge_detail cd, participation p " +
            "where cd.challenge_detail_id = p.challenge_detail_id" +
            "and p.account_id = :id", nativeQuery = true)
    TodayChallengeRes getTodayChallenge(@Param(value = "id") Long id);

    @Query(value = "SELECT * FROM challenge_detail c " +
            "WHERE c.challenge_id = :id " +
            "ORDER BY c.create_date ASC", nativeQuery = true)
    List<ChallengeDetail> findByChallengeDetailByLatest(@Param(value = "id") Long id);
}
