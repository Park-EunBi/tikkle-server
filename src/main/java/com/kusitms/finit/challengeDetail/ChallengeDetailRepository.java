package com.kusitms.finit.challengeDetail;

import com.kusitms.finit.challenge.dto.TodayChallengeRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
            "ORDER BY c.create_date", nativeQuery = true)
    List<ChallengeDetail> findByChallengeDetailByLatest(@Param(value = "id") Long id);

    @Query(value = "SELECT * FROM challenge_detail c " +
            "WHERE c.challenge_id = :id " +
            "ORDER BY c.participate DESC", nativeQuery = true)
    List<ChallengeDetail> findByChallengeDetailByParticipate(@Param(value = "id") Long id);

    @Modifying
    @Query(value = "UPDATE challenge_detail c " +
            "SET c.participate = :num WHERE c.challenge_detail_id = :id", nativeQuery = true)
    void updateParticipate(@Param(value = "num") int num, @Param(value = "id") Long id);

}
