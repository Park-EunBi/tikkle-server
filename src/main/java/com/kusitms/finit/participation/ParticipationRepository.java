package com.kusitms.finit.participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT c.title " +
            "FROM participation p, challenge_detail c " +
            "WHERE p.challenge_detail_id = c.challenge_detail_id " +
            "AND p.account_id = :id " +
            "AND p.certificate = false", nativeQuery = true)
    List<String> findTitleByAccountIdAndCertificate(@Param(value = "id") Long id);
}
