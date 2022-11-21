package com.kusitms.finit.participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query(value = "SELECT c.title FROM participation p, challenge_detail c " +
            "where p.account_id = c.account_id", nativeQuery = true)
    List<String> findByAccountId(Long accountId);
}
