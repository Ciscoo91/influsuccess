package com.ffu.repository.custom;

import com.ffu.domain.Campaign;
import com.ffu.domain.Campaign_;
import com.ffu.domain.User;
import com.ffu.domain.User_;
import com.ffu.repository.common.AbstractDAO;
import com.ffu.repository.dto.CampaignSearchDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;


import javax.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import java.util.LinkedList;
import java.util.List;

public class CampaignRepositoryCustomImpl extends AbstractDAO implements  CampaignRepositoryCustom{

    @Override
    public Page<Campaign> getCampaignPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable) {
        CriteriaQuery<Campaign> campaignCriteriaQuery = builder.createQuery(Campaign.class);

        Root<Campaign> campaignRoot = campaignCriteriaQuery.from(Campaign.class);

        List<Predicate> predicates = new LinkedList<>();
        this.buildPredicates(predicates,campaignSearchDTO,campaignRoot, campaignCriteriaQuery);

        campaignCriteriaQuery.select(campaignRoot)
            .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(campaignRoot));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return this.getResultPageable(campaignRoot,campaignCriteriaQuery, count, pageable);
    }


    private void buildPredicates(List<Predicate> predicates,CampaignSearchDTO campaignSearchDTO, Root<Campaign> campaignRoot, CriteriaQuery criteriaQuery) {

        if(StringUtils.isNotBlank(campaignSearchDTO.getTitle())){
            this.addPredicate(predicates, campaignRoot.get(Campaign_.TITLE), campaignSearchDTO.getTitle());
        }

        if(StringUtils.isNotBlank(campaignSearchDTO.getUserLogin())){
            Join<Campaign, User> userJoin = campaignRoot.join(Campaign_.USER);
            this.addPredicate(predicates, userJoin.get(User_.LOGIN), campaignSearchDTO.getUserLogin());
        }

    }

}
