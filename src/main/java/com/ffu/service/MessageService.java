package com.ffu.service;

import com.ffu.service.dto.MessageDTO;
import java.util.List;
import java.util.Optional;

public interface MessageService {

    MessageDTO save(MessageDTO messageDTO);

    Optional<MessageDTO> findById(Long id);

    List<MessageDTO> findAll();

    void deleteById(Long id);

    List<MessageDTO> getLastUserMessageForCampaigns(Long userId);

    Long getCountNewMessages(Long userId, Long campaignId);
}
