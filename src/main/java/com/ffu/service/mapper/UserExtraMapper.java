package com.ffu.service.mapper;

import com.ffu.domain.UserExtra;
import com.ffu.service.dto.UserExtraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserExtraMapper {


    UserExtra userExtraDTOToUserExtra(UserExtraDTO userExtraDTO);

    UserExtraDTO userExtraToUserExtraDTO(UserExtra userExtra);

}
