package com.ffu.service.Impl;

import com.ffu.domain.Discussion;
import com.ffu.domain.enumeration.MessageStatus;
import com.ffu.repository.DiscussionRepository;
import com.ffu.repository.MessageRepository;
import com.ffu.service.DiscussionService;
import com.ffu.service.MessageService;
import com.ffu.service.dto.ChatDTO;
import com.ffu.service.dto.DiscussionDTO;
import com.ffu.service.dto.DiscussionThreadsDTO;
import com.ffu.service.mapper.DiscussionMapper;
import com.ffu.service.mapper.MessageMapper;
import com.ffu.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final MessageRepository messageRepository;
    private final DiscussionMapper discussionMapper = DiscussionMapper.INSTANCE;
    private final MessageService messageService;
    private final MessageMapper messageMapper = MessageMapper.INSTANCE;
    private final UserMapper userMapper;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository, MessageRepository messageRepository, MessageService messageService, UserMapper userMapper) {
        this.discussionRepository = discussionRepository;
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.userMapper = userMapper;
    }

    @Override
    public DiscussionDTO save(DiscussionDTO discussionDTO) {
        Discussion discussion = discussionMapper.toEntity(discussionDTO);
        return discussionMapper.toDTO(discussionRepository.save(discussion));
    }

    @Override
    public List<DiscussionDTO> findAll() {
        return discussionRepository.findAll()
            .stream()
            .map(discussionMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<DiscussionDTO> findById(Long id) {
        return discussionRepository.findById(id).map(discussionMapper::toDTO);
    }

    @Override
    public void deleteById(Long id) {
        discussionRepository.deleteById(id);
    }

    @Override
    public ChatDTO getChatDiscussion(Long discussionId, Long userId ) {
        ChatDTO chat = new ChatDTO();

        Optional<Discussion> discussion = discussionRepository.findById(discussionId);
        if(discussion.isPresent()){
            chat.setChatTitle(discussion.get().getCampaign().getTitle());
            chat.setMessages(
                discussion.get().getMessages()
                    .stream().
                    map(message -> messageMapper.toChatMessage(message, userId))
                    .collect(Collectors.toList())
            );
            chat.setParticipants(discussion.get().getParticipants().stream().map(userMapper::toParticipantChat).collect(Collectors.toList()));
        }
        discussion.get().getMessages().stream()
            .filter(message -> !message.getSender().getId().equals(userId))
            .forEach(message -> {
                message.setStatus(MessageStatus.OPENED);
                messageRepository.saveAndFlush(message);
            });
        return chat;
    }

    @Override
    public List<DiscussionThreadsDTO> findAllByUser(Long userId) {

        List<DiscussionThreadsDTO> result =  discussionRepository.findByParticipant(userId)
            .stream()
            .map(discussionMapper::toDiscussionThreadsDTO)
            .sorted(Comparator.comparingLong(DiscussionThreadsDTO::getDiscussionId)
                .reversed())
            .collect(Collectors.toList());

        result.stream().forEach(discussionThreadsDTO ->
            discussionThreadsDTO.setCountNewMessages(messageService.getCountNewMessages(userId, discussionThreadsDTO.getDiscussionId())));
        return result;
    }

}
