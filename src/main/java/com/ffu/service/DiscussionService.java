package com.ffu.service;

import com.ffu.service.dto.DiscussionThreadsDTO;

import java.util.List;

public interface DiscussionService {

    List<DiscussionThreadsDTO> findAllByUser(Long userId);
}
