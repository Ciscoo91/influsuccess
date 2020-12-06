package com.ffu.repository;

import com.ffu.domain.SocialNetworkLink;

import com.ffu.domain.enumeration.SocialNetworkEnum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the SocialNetworkLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialNetworkLinkRepository extends JpaRepository<SocialNetworkLink, Long> {
    Optional<SocialNetworkLink> findByInfluencer_idAndSocialNetwork_name(Long id, SocialNetworkEnum socialNetworkName);
}
