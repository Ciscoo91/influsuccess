package com.ffu.repository.custom;

import com.ffu.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepositoryCustom {

    Optional<Message> getLastUserMessageForACampaign(Long userId, Long campaignId);

    List<Message> getLastUserMessageForCampaigns(Long userId);

    Long getCountNewMessages(Long userId, Long campaignId);
}
