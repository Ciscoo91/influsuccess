package com.ffu.service.mapper;

import com.ffu.domain.Message;
import com.ffu.service.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "sender.login", target = "senderLogin")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "receiver.login", target = "receiverLogin")
    MessageDTO toDto(Message message);

    Message toEntity(MessageDTO messageDTO);

}
