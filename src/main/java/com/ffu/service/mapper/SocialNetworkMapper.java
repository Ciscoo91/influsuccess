package com.ffu.service.mapper;

import com.ffu.domain.SocialNetwork;
import com.ffu.domain.enumeration.SocialNetworkEnum;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialNetworkMapper {


    public default SocialNetwork socialNetworkFromId(SocialNetworkEnum name) {
        if (name == null) {
            return null;
        }
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setName(name);
        return socialNetwork;
    }
}
