package com.kusitms.finit.retrospect;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {

    Optional<Retrospect> findByAccountIdAndCreateDate(Long accountId, String date);
}
