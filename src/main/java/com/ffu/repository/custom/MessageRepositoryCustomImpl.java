package com.ffu.repository.custom;


import com.ffu.domain.*;
import com.ffu.domain.enumeration.MessageStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    private final EntityManager entityManager;
    private  CriteriaBuilder builder;

    public MessageRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        builder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Optional<Message> getLastUserMessageForACampaign(Long userId, Long campaignId) {
        CriteriaQuery<Message> criteriaQuery = builder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
       /* criteriaQuery.select(root)
            .where(
                builder.and(
                builder.equal(root.get(Message_.RECEIVER).get(User_.ID), userId),
                builder.equal(root.get(Message_.).get(Campaign_.ID), campaignId))
            )
            .orderBy(builder.desc(root.get(Message_.ID)));

        try {
            return  Optional.of(entityManager.createQuery(criteriaQuery).setMaxResults(1).getSingleResult());
        }catch (NoResultException exception){
            return Optional.empty();
        }*/
       return null;
    }

    @Override
    public List<Message> getLastUserMessageForDiscussion(Long userId) {
        List<Optional<Message>> messages = new ArrayList<>();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Campaign> root = criteriaQuery.from(Campaign.class);
            criteriaQuery.select(root.get(Campaign_.ID))
            .where(builder.equal(root.get(Campaign_.USER),userId));
        List<Long> campaignIds =  entityManager.createQuery(criteriaQuery).getResultList();

        for(Long campaignId: campaignIds) {
            messages.add(getLastUserMessageForACampaign(userId, campaignId));
        }
        return messages.stream().filter(value -> value.isPresent()).map(Optional::get).collect(Collectors.toList());
    }

    @Override
    public Long getCountNewMessages(Long userId, Long discussionId) {
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Message> root = criteriaQuery.from(Message.class);

        criteriaQuery.select(builder.countDistinct(root.get(Message_.ID))).where(
                builder.and(
                    builder.equal(root.get(Message_.DISCUSSION).get(Discussion_.ID),discussionId),
                builder.equal(root.get(Message_.STATUS), MessageStatus.SENT),
                    builder.notEqual(root.get(Message_.SENDER).get(User_.ID),userId)
                ));
      return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
