package com.ffu.repository;

import com.ffu.domain.InfluencerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InfluencerInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfluencerInfoRepository extends JpaRepository<InfluencerInfo, Long> {
}
