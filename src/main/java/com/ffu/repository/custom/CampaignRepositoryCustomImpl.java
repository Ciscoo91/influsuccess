package com.ffu.repository.custom;

import com.ffu.domain.Campaign;
import com.ffu.repository.dto.CampaignSearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.util.List;

public class CampaignRepositoryCustomImpl  implements  CampaignRepositoryCustom{
    private final EntityManager entityManager;
    private CriteriaBuilder builder;

    public CampaignRepositoryCustomImpl(EntityManager entityManager, CriteriaBuilder builder) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Campaign> getCampaignPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable) {

        return null;
    }
}
