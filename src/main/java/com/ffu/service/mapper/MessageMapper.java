package com.ffu.service.mapper;

import com.ffu.domain.Discussion;
import com.ffu.domain.Message;
import com.ffu.domain.enumeration.MessageStatus;
import com.ffu.service.dto.MessageDTO;
import com.ffu.service.dto.MessageChat;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public abstract class MessageMapper {

    public static MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "sender.login", target = "senderLogin")
    public abstract MessageDTO toDto(Message message);

    public abstract Message toEntity(MessageDTO messageDTO);

    @Mapping(source = "message.sender.id", target = "participantId")
    @Mapping(source = "message.createdDate", target = "timestamp")
    public abstract MessageChat toChatMessage(Message message, @Context Long userId);

    @Mapping(source = "participantId", target = "sender")
    public abstract Message toEntity(MessageChat messageChat, @Context Discussion discussion);

    @AfterMapping
     protected void setMySelf(Message message, @MappingTarget MessageChat messageChat, @Context Long userId){
        messageChat.setMyself(message.getSender().getId().equals(userId));
    }
    @AfterMapping
    protected void fillMessageFromMessageChat(MessageChat messageChat, @MappingTarget Message message, @Context Discussion discussion){
        message.setDiscussion(discussion);
        message.setStatus(MessageStatus.SENT);
    }

}
