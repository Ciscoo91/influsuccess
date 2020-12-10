package com.ffu.service;

import com.ffu.service.dto.MessageChat;
import com.ffu.service.dto.MessageDTO;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    MessageDTO save(MessageDTO messageDTO);

    Optional<MessageDTO> findById(Long id);

    List<MessageDTO> findAll();

    void deleteById(Long id);

    Long getCountNewMessages(Long userId, Long discussionId);

    Long getAlllNewMessageCount(Long userId);

    MessageChat saveMessageChat(MessageChat messageChat, Long discussionId);
}
