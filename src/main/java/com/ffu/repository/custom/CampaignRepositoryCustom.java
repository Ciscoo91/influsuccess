package com.ffu.repository.custom;

import com.ffu.domain.Campaign;
import com.ffu.repository.dto.CampaignSearchDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface CampaignRepositoryCustom {

    List<Campaign> getCampaignPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable);
}
