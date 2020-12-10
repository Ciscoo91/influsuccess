package com.ffu.repository;

import com.ffu.domain.Influencer;
import com.ffu.repository.custom.InfluencerRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Influencer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long>, InfluencerRepositoryCustom {
    Optional<Influencer> findByUsername(String username);
    
}
