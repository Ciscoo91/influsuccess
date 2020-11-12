package com.ffu.repository;

import com.ffu.domain.Discussion;

import com.ffu.repository.custom.DiscussionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Discussion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiscussionRepository extends DiscussionRepositoryCustom,JpaRepository<Discussion, Long> {

}
