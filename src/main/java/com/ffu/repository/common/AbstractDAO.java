package com.ffu.repository.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public abstract class AbstractDAO {

    @Autowired
    protected EntityManager entityManager;

    protected CriteriaBuilder builder = entityManager.getCriteriaBuilder();

    protected Page getResultPageable(Root root, CriteriaQuery criteriaQuery, Long count, Pageable pageable) {

        criteriaQuery.orderBy(QueryUtils.toOrders(pageable.getSort(),root,builder));

        TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber()* pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl(typedQuery.getResultList(),pageable, count);
    }

    protected void addPredicate(List<Predicate> predicates, Expression<String> field, String filter) {
        if(filter.contains("*")){
            predicates.add(builder.like(field,filter));
        } else {
            predicates.add(builder.equal(field, filter));
        }
    }
}
