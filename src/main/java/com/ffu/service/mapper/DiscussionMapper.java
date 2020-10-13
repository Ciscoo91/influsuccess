package com.ffu.service.mapper;

import com.ffu.domain.Discussion;
import com.ffu.domain.Message;
import com.ffu.domain.User;
import com.ffu.service.dto.DiscussionThreadsDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public abstract class DiscussionMapper {

    public static DiscussionMapper INSTANCE = Mappers.getMapper(DiscussionMapper.class);

    @Mapping(source = "id", target = "discussionId")
    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "campaign.title", target = "campaignTitle")
    @Mapping(target = "countNewMessages", ignore = true)
    public abstract DiscussionThreadsDTO toDiscussionThreadsDTO(Discussion discussion);

    @AfterMapping
    protected void setParticipantsAndLastMessage(Discussion discussion,@MappingTarget DiscussionThreadsDTO discussionThreadsDTO){
        Set<User> participants = discussion.getParticipants();
        discussionThreadsDTO.setLoginParticipants(
                participants
                .stream()
                .map(User::getLogin)
                .collect(Collectors.toList())
        );

        Optional<Message> optionalMessage = discussion.getMessages().stream().max(Comparator.comparing(Message::getId));

        if(optionalMessage.isPresent()){
            discussionThreadsDTO.setLastMessage(optionalMessage.get().getContent());
        }

    }
}
