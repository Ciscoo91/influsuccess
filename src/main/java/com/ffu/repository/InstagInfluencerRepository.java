package com.ffu.repository;

import com.ffu.domain.InstagInfluencer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InstagInfluencer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstagInfluencerRepository extends JpaRepository<InstagInfluencer, Long> {
}
