package com.ffu.service.mapper;

import com.ffu.domain.Influencer;
import com.ffu.service.dto.InfluencerDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {SocialNetworkLinkMapper.class, CountryMapper.class}, componentModel = "spring")
public interface  InfluencerMapper {

    @Mapping(target = "totalFollowers", expression =
     "java(influencer.getSocialNetworkLinks().stream().mapToLong(SocialNetworkLink::getFollower).sum())")

    public InfluencerDTO toDto(Influencer influencer);

    public Influencer toEntity(InfluencerDTO influencerDTO);

    public default Influencer influencerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Influencer influencer = new Influencer();
        influencer.setId(id);
        return influencer;
    }
}
