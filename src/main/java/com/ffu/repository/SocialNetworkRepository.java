package com.ffu.repository;

import java.util.Optional;

import com.ffu.domain.SocialNetwork;
import com.ffu.domain.enumeration.SocialNetworkEnum;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SocialNetwork entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, String> {
    Optional<SocialNetwork> findByName(SocialNetworkEnum socialNetworkEnum);
}
