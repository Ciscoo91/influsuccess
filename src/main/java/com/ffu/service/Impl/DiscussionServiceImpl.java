package com.ffu.service.Impl;

import com.ffu.repository.DiscussionRepository;
import com.ffu.service.DiscussionService;
import com.ffu.service.MessageService;
import com.ffu.service.dto.DiscussionThreadsDTO;
import com.ffu.service.mapper.DiscussionMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper discussionMapper = DiscussionMapper.INSTANCE;
    private final MessageService messageService;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository, MessageService messageService) {
        this.discussionRepository = discussionRepository;
        this.messageService = messageService;
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
            discussionThreadsDTO.setCountNewMessages(messageService.getCountNewMessages(userId,discussionThreadsDTO.getCampaignId())));
    return result;
    }
}
