package com.ffu.service.dto;

import java.util.Set;

public class DiscussionDTO {
    private Long id;

    private Set<Long> participantIds;

    private Set<MessageDTO> messages;

    private CampaignDTO campaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(Set<Long> participants) {
        this.participantIds = participants;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
    }
}
