package com.ffu.repository;

import com.ffu.domain.Campaign;

import com.ffu.domain.enumeration.CampaignStatus;
import com.ffu.repository.custom.CampaignRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends CampaignRepositoryCustom,JpaRepository<Campaign, Long> {

    @Query("select campaign from Campaign campaign where campaign.user.login = ?#{principal.username}")
    List<Campaign> findByUserIsCurrentUser();

    List<Campaign> findAllByStatusOrderByIdAsc(CampaignStatus campaignStatus);
}
