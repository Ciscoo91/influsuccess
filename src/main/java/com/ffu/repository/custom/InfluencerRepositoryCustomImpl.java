package com.ffu.repository.custom;

import com.ffu.domain.CampaignCategory;
import com.ffu.domain.CampaignCategory_;
import com.ffu.domain.Country;
import com.ffu.domain.Country_;
import com.ffu.domain.Influencer;
import com.ffu.domain.Influencer_;
import com.ffu.domain.SocialNetwork;
import com.ffu.domain.SocialNetwork_;
import com.ffu.repository.common.AbstractDAO;
import com.ffu.repository.dto.InfluencerSearchDTO;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class InfluencerRepositoryCustomImpl extends AbstractDAO implements InfluencerRepositoryCustom {

    @Override
    public Page<Influencer> getInfluencerPageable(InfluencerSearchDTO influencerSearchDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Influencer> influencerCriteriaQuery = builder.createQuery(Influencer.class);

        Root<Influencer> influencerRoot = influencerCriteriaQuery.from(Influencer.class);

        List<Predicate> predicates = new LinkedList<>();
        this.buildPredicates(predicates, influencerSearchDTO, influencerRoot, influencerCriteriaQuery, builder);

        influencerCriteriaQuery.select(influencerRoot).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(Influencer.class)));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return this.getResultPageable(influencerRoot, influencerCriteriaQuery, count, pageable, builder);
    }

    private void buildPredicates(
        List<Predicate> predicates,
        InfluencerSearchDTO influencerSearchDTO,
        Root<Influencer> influencerRoot,
        CriteriaQuery<Influencer> cq,
        CriteriaBuilder builder
    ) {
        // if (StringUtils.isNotBlank(influencerSearchDTO.getUsername())) {
        //     this.addPredicate(predicates, influencerRoot.get(Influencer_.USERNAME), influencerSearchDTO.getUsername(), builder);
        // }

        // if (
        //     influencerSearchDTO.getSocialNetworkName() != null
        // ) {
        //     Root<SocialNetwork> socialNetworkRoot = cq.from(SocialNetwork.class);
        //     this.addPredicate(
        //             predicates,
        //             socialNetworkRoot.get(SocialNetwork_.NAME),
        //             influencerSearchDTO.getSocialNetworkName(),
        //             builder
        //         );
        // }

        // if (influencerSearchDTO.getCampaignCategoryEnum() != null) {
        //     Root<CampaignCategory> campaignCategoryRoot = cq.from(CampaignCategory.class);
        //     this.addPredicate(
        //             predicates,
        //             campaignCategoryRoot.get(CampaignCategory_.NAME),
        //             influencerSearchDTO.getCampaignCategoryEnum(),
        //             builder
        //         );
        // }

        // if (influencerSearchDTO.getCountryCode() != null) {
        //     Root<Country> countryRoot = cq.from(Country.class);
        //     this.addPredicate(predicates, countryRoot.get(Country_.CODE), influencerSearchDTO.getCountryCode(), builder);
        // }
    }
}
