package com.ffu.service;

import com.ffu.service.dto.ChatDTO;
import com.ffu.service.dto.DiscussionDTO;
import com.ffu.service.dto.DiscussionThreadsDTO;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {

    ChatDTO getChatDiscussion(Long discussionId, Long userId);

    List<DiscussionThreadsDTO> findAllByUser(Long userId);

    DiscussionDTO save(DiscussionDTO discussion);

    List<DiscussionDTO> findAll();

    Optional<DiscussionDTO> findById(Long id);

    void deleteById(Long id);

    ChatDTO isAlreadyExistByParticipantAndCampaign(Long userId, Long campaignId);
}
