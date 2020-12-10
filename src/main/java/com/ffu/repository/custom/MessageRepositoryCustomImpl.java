package com.ffu.repository.custom;

import com.ffu.domain.Discussion_;
import com.ffu.domain.Message;
import com.ffu.domain.Message_;
import com.ffu.domain.User_;
import com.ffu.domain.enumeration.MessageStatus;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {
    private final EntityManager entityManager;
    private CriteriaBuilder builder;

    public MessageRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        builder = entityManager.getCriteriaBuilder();
    }

    @Override
    public Long getCountNewMessages(Long userId, Long discussionId) {
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Message> root = criteriaQuery.from(Message.class);

        criteriaQuery
            .select(builder.countDistinct(root.get(Message_.ID)))
            .where(
                builder.and(
                    builder.equal(root.get(Message_.DISCUSSION).get(Discussion_.ID), discussionId),
                    builder.equal(root.get(Message_.STATUS), MessageStatus.SENT),
                    builder.notEqual(root.get(Message_.SENDER).get(User_.ID), userId)
                )
            );
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Long getAlllNewMessageCount(Long userId) {
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Message> root = criteriaQuery.from(Message.class);

        criteriaQuery
            .select(builder.countDistinct(root.get(Message_.ID)))
            .where(
                builder.and(
                    builder.equal(root.get(Message_.STATUS), MessageStatus.SENT),
                    builder.notEqual(root.get(Message_.SENDER).get(User_.ID), userId)
                )
            );
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
