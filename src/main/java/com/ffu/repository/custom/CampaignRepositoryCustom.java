package com.ffu.repository.custom;

import com.ffu.domain.Campaign;
import com.ffu.repository.dto.CampaignSearchDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface CampaignRepositoryCustom {

    Page<Campaign> getCampaignPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable);
}
