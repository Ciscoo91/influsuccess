package com.ffu.service.mapper;

import com.ffu.domain.SocialNetwork;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialNetworkMapper {


    public default SocialNetwork socialNetworkFromId(String name) {
        if (name == null) {
            return null;
        }
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setName(name);
        return socialNetwork;
    }
}
