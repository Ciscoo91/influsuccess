package com.ffu.repository.custom;


import com.ffu.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class DiscussionRepositoryCustomImpl implements DiscussionRepositoryCustom {

    private final EntityManager entityManager;
    private CriteriaBuilder builder;

    public DiscussionRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Discussion> findByParticipant(Long userId) {
        CriteriaQuery<Discussion> criteriaQuery = builder.createQuery(Discussion.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Discussion> join = root.join(User_.DISCUSSIONS);
        join.on(builder.equal(root.get(User_.ID),userId));
        criteriaQuery.select(join);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<Discussion> findByParticipantAndCampaign(Long userId, Long campaignId) {
        CriteriaQuery<Discussion> criteriaQuery = builder.createQuery(Discussion.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Discussion> join = root.join(User_.DISCUSSIONS);
        join.on(
            builder.and(
                builder.equal(root.get(User_.ID), userId),
                builder.equal(join.get(Discussion_.CAMPAIGN).get(Campaign_.ID), campaignId)
            )
        );
        criteriaQuery.select(join);
        try {
            return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }
}
