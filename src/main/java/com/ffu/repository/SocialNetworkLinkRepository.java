package com.ffu.repository;

import com.ffu.domain.SocialNetworkLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SocialNetworkLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialNetworkLinkRepository extends JpaRepository<SocialNetworkLink, Long> {
    Optional<SocialNetworkLink> findByInfluencer_idAndSocialNetwork_name(Long id, String socialNetworkName);

    List<SocialNetworkLink> findAllBySocialNetwork_Name(String socialNetworkName);
}
