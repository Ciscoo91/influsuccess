package com.ffu.repository.custom;

import com.ffu.domain.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionRepositoryCustom {

    List<Discussion> findByParticipant(Long userId);

    Optional<Discussion> findByParticipantAndCampaign(Long userId, Long campaignId);
}
