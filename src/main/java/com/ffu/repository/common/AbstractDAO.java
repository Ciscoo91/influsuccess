package com.ffu.repository.common;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

public class AbstractDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Page getResultPageable(Root root, CriteriaQuery criteriaQuery, Long count, Pageable pageable, CriteriaBuilder builder) {
     
        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));

        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl(typedQuery.getResultList(), pageable, count);
    }

    protected void addPredicate(List<Predicate> predicates, Expression<String> field, String filter, CriteriaBuilder builder) {
        if (filter.contains("*")) {
            filter = filter.substring(0, filter.length() - 1) + "%";
            predicates.add(builder.like(field, filter));
        } else {
            predicates.add(builder.equal(field, filter));
        }
    }
}
