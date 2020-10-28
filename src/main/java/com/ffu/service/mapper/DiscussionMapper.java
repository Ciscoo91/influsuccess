package com.ffu.service.mapper;

import com.ffu.domain.Discussion;
import com.ffu.domain.Message;
import com.ffu.domain.User;
import com.ffu.service.UserService;
import com.ffu.service.dto.DiscussionDTO;
import com.ffu.service.dto.DiscussionThreadsDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {UserMapper.class}, componentModel = "spring")
public abstract class DiscussionMapper {

    @Autowired
    protected UserService userService;

    @Mapping(source = "id", target = "discussionId")
    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "campaign.title", target = "campaignTitle")
    @Mapping(target = "countNewMessages", ignore = true)
    public abstract DiscussionThreadsDTO toDiscussionThreadsDTO(Discussion discussion);

    public abstract DiscussionDTO toDTO(Discussion discussion);

    @Mapping(target = "participants",  expression = "java(userService.getUsersFromIds(discussionDTO.getParticipantIds()))")
    public abstract Discussion toEntity(DiscussionDTO discussionDTO);


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

    public Discussion discussionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Discussion discussion = new Discussion();
        discussion.setId(id);
        return discussion;
    }
}
