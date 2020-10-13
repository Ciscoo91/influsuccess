package com.ffu.repository.custom;

import com.ffu.domain.Discussion;

import java.util.Collection;
import java.util.List;

public interface DiscussionRepositoryCustom {

    List<Discussion> findByParticipant(Long userId);
}
