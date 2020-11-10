package com.ffu.repository.custom;

import com.ffu.domain.User;
import com.ffu.domain.User_;
import com.ffu.repository.common.AbstractDAO;
import com.ffu.repository.dto.UserSearchDTO;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl extends AbstractDAO implements UserRepositoryCustom {
    @Override
    public Page<User> getUserPageable(UserSearchDTO userSearchDTO, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);

        List<Predicate> predicates = new LinkedList<>();
        this.buildPredicates(predicates, userSearchDTO, userRoot, builder);

        userCriteriaQuery.select(userRoot)
            .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(User.class)));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return this.getResultPageable(userRoot, userCriteriaQuery, count, pageable, builder);
    }

    public void buildPredicates(List<Predicate> predicates, UserSearchDTO userSearchDTO, Root<User> userRoot, CriteriaBuilder builder){
        if(StringUtils.isNotBlank(userSearchDTO.getLogin())){
            this.addPredicate(predicates, userRoot.get(User_.login), userSearchDTO.getLogin(), builder);
        }

        if(StringUtils.isNotBlank(userSearchDTO.getEmail())){
            this.addPredicate(predicates, userRoot.get(User_.email), userSearchDTO.getEmail(), builder);
        }

        if(StringUtils.isNotBlank(userSearchDTO.getFirstName())){
            this.addPredicate(predicates, userRoot.get(User_.firstName), userSearchDTO.getFirstName(), builder);
        }

        if(StringUtils.isNotBlank(userSearchDTO.getLastName())){
            this.addPredicate(predicates, userRoot.get(User_.lastName), userSearchDTO.getLastName(), builder);
        }
    }
}
