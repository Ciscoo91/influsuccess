package com.ffu.service.Impl;

import com.ffu.domain.Influencer;
import com.ffu.repository.InfluencerRepository;
import com.ffu.repository.dto.InfluencerSearchDTO;
import com.ffu.service.InfluencerService;
import com.ffu.service.dto.InfluencerDTO;
import com.ffu.service.mapper.InfluencerMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerMapper influencerMapper;
    private final InfluencerRepository influencerRepository;

    public InfluencerServiceImpl(InfluencerMapper influencerMapper, InfluencerRepository influencerRepository) {
        this.influencerMapper = influencerMapper;
        this.influencerRepository = influencerRepository;
    }

    @Override
    public InfluencerDTO save(InfluencerDTO InfluencerDTO) {
        Influencer influencer = influencerMapper.toEntity(InfluencerDTO);
        return influencerMapper.toDto(influencerRepository.save(influencer));
    }

    @Override
    public Optional<InfluencerDTO> findById(Long id) {
        return influencerRepository.findById(id).map(influencerMapper::toDto);
    }

    @Override
    public List<InfluencerDTO> findAll() {
        return influencerRepository.findAll()
            .stream()
            .map(influencerMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        influencerRepository.deleteById(id);
    }

    @Override
    public Page<InfluencerDTO> getInfluencerSearchPageable(InfluencerSearchDTO InfluencerSearchDTO, Pageable pageable) {
           return influencerRepository.getInfluencerPageable(InfluencerSearchDTO, pageable)
                .map(influencerMapper::toDto);
    }

}
