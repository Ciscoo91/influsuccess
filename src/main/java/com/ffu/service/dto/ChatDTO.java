package com.ffu.service.dto;

import java.util.List;

public class ChatDTO {
    private String chatTitle;
    private List<ParticipantChat> participants;
    private List<MessageChat> messages;
    private Long discussionId ;

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public List<ParticipantChat> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantChat> participants) {
        this.participants = participants;
    }

    public List<MessageChat> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageChat> messages) {
        this.messages = messages;
    }

    public Long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }
}
