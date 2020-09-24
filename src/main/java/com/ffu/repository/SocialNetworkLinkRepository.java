package com.ffu.repository;

import com.ffu.domain.SocialNetworkLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SocialNetworkLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialNetworkLinkRepository extends JpaRepository<SocialNetworkLink, Long> {
}
