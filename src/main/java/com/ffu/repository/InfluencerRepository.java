package com.ffu.repository;

import com.ffu.domain.Influencer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Influencer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    Optional<Influencer> findByUsername(String username);
}
