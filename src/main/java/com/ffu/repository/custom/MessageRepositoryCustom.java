package com.ffu.repository.custom;

public interface MessageRepositoryCustom {

    Long getCountNewMessages(Long userId, Long discussionId);

    Long getAlllNewMessageCount(Long userId);
}
