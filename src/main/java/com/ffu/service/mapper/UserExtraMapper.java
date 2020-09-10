package com.ffu.service.mapper;

import com.ffu.domain.UserExtra;
import com.ffu.service.dto.UserExtraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserExtraMapper {

    UserExtraMapper INSTANCE = Mappers.getMapper(UserExtraMapper.class);

    UserExtra userExtraDTOToUserExtra(UserExtraDTO userExtraDTO);

    UserExtraDTO userExtraToUserExtraDTO(UserExtra userExtra);

}
