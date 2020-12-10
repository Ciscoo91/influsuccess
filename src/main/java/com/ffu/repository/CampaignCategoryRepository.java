package com.ffu.repository;

import java.util.Optional;

import com.ffu.domain.CampaignCategory;
import com.ffu.domain.enumeration.CampaignCategoryEnum;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CampaignCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignCategoryRepository extends JpaRepository<CampaignCategory, String> {
   void deleteByName(CampaignCategoryEnum name);

Optional<CampaignCategory> findByName(CampaignCategoryEnum name);
}
