package com.ffu.service.mapper;

import com.ffu.domain.Campaign;
import com.ffu.service.dto.CampaignDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = UserMapper.class, componentModel = "spring")
public interface CampaignMapper {

    @Mapping(source = "userId", target = "user")
    Campaign toEntity(CampaignDTO campaignDTO);

    @Mapping(source="user.id", target = "userId")
    @Mapping(source="user.login", target = "userLogin")
    CampaignDTO toDto(Campaign campaign);

    public default Campaign campaignFromId(Long id) {
        if (id == null) {
            return null;
        }
        Campaign campaign = new Campaign();
        campaign.setId(id);
        return campaign;
    }
}
