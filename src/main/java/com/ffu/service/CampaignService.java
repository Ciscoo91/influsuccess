package com.ffu.service;

import com.ffu.repository.dto.CampaignSearchDTO;
import com.ffu.service.dto.CampaignDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CampaignService {

    CampaignDTO save(CampaignDTO campaignDTO);

    Optional<CampaignDTO> findById(Long id);

    List<CampaignDTO> findAll();

    void deleteById(Long id);

    /**
     * get all opened the campaigns.
     * @return
     */
    List<CampaignDTO> getOpenedCampaigns();


    List<CampaignDTO> getCampaignSearchPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable);
}
