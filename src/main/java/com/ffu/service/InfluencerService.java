package com.ffu.service;

import com.ffu.repository.dto.InfluencerSearchDTO;
import com.ffu.service.dto.InfluencerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface InfluencerService {

    InfluencerDTO save(InfluencerDTO influencerDTO);

    Optional<InfluencerDTO> findById(Long id);

    List<InfluencerDTO> findAll();

    void deleteById(Long id);

    Page<InfluencerDTO> getInfluencerSearchPageable(InfluencerSearchDTO influencerSearchDTO, Pageable pageable);
}
