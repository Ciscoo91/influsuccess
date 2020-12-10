package com.ffu.repository.custom;

import com.ffu.domain.Influencer;
import com.ffu.repository.dto.InfluencerSearchDTO;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface InfluencerRepositoryCustom {
    Page<Influencer> getInfluencerPageable(InfluencerSearchDTO influencerSearchDTO, Pageable pageable);
}
