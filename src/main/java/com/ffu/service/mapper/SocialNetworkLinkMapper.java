package com.ffu.service.mapper;

import com.ffu.domain.SocialNetworkLink;
import com.ffu.service.dto.SocialNetworkLinkDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses={SocialNetworkMapper.class},componentModel = "spring")
public interface SocialNetworkLinkMapper {

    @Mapping(source = "socialNetworkName", target = "socialNetwork")
    SocialNetworkLink toEntity(SocialNetworkLinkDTO socialNetworkDTO);

    @Mapping(source="socialNetwork.name", target = "socialNetworkName")
    SocialNetworkLinkDTO toDto(SocialNetworkLink socialNetworkLink);

    public default SocialNetworkLink socialNetworkLinkFromId(Long id) {
        if (id == null) {
            return null;
        }
        SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
        socialNetworkLink.setId(id);
        return socialNetworkLink;
    }
}
