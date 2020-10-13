package com.ffu.repository;

import com.ffu.domain.Campaign;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("select campaign from Campaign campaign where campaign.user.login = ?#{principal.username}")
    List<Campaign> findByUserIsCurrentUser();
}
