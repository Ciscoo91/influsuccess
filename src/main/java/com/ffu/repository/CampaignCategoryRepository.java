package com.ffu.repository;

import com.ffu.domain.CampaignCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CampaignCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignCategoryRepository extends JpaRepository<CampaignCategory, Long> {
}
