package com.ffu.service.Impl;

import com.ffu.domain.Message;
import com.ffu.repository.MessageRepository;
import com.ffu.service.MessageService;
import com.ffu.service.dto.MessageDTO;
import com.ffu.service.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper = MessageMapper.INSTANCE;
    private final MessageRepository messageRepository;

    public MessageServiceImpl( MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        Message message = messageMapper.toEntity(messageDTO);
        return messageMapper.toDto(messageRepository.save(message));
    }

    @Override
    public Optional<MessageDTO> findById(Long id) {
        return messageRepository.findById(id).map(messageMapper::toDto);
    }

    @Override
    public List<MessageDTO> findAll() {
        return messageRepository.findAll()
            .stream()
            .map(messageMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDTO> getLastUserMessageForCampaigns(Long userId) {
        return messageRepository.getLastUserMessageForCampaigns(userId).stream().map(messageMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Long getCountNewMessages(Long userId, Long campaignId) {
        return messageRepository.getCountNewMessages(userId, campaignId);
    }


}
