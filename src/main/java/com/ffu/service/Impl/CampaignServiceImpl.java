package com.ffu.service.Impl;

import com.ffu.domain.Campaign;
import com.ffu.domain.enumeration.CampaignStatus;
import com.ffu.repository.CampaignRepository;
import com.ffu.repository.dto.CampaignSearchDTO;
import com.ffu.service.CampaignService;
import com.ffu.service.dto.CampaignDTO;
import com.ffu.service.mapper.CampaignMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignMapper campaignMapper;
    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignMapper campaignMapper, CampaignRepository campaignRepository) {
        this.campaignMapper = campaignMapper;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public CampaignDTO save(CampaignDTO campaignDTO) {
        Campaign campaign = campaignMapper.toEntity(campaignDTO);
        return campaignMapper.toDto(campaignRepository.save(campaign));
    }

    @Override
    public Optional<CampaignDTO> findById(Long id) {
        return campaignRepository.findById(id).map(campaignMapper::toDto);
    }

    @Override
    public List<CampaignDTO> findAll() {
        return campaignRepository.findAll()
            .stream()
            .map(campaignMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        campaignRepository.deleteById(id);
    }

    /**{@inheritDoc}**/
    @Override
    public List<CampaignDTO> getOpenedCampaigns() {
        return campaignRepository.findAllByStatusOrderByIdAsc(CampaignStatus.OPENED)
            .stream()
            .map(campaignMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<CampaignDTO> getCampaignSearchPageable(CampaignSearchDTO campaignSearchDTO, Pageable pageable) {
           return campaignRepository.getCampaignPageable(campaignSearchDTO, pageable)
                .map(campaignMapper::toDto);
    }
}
