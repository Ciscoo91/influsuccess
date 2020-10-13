package com.ffu.service.dto;

import java.util.List;

public class DiscussionThreadsDTO {

    private Long discussionId;
    private Long campaignId;
    private String campaignTitle;
    private List<String> loginParticipants;
    private String lastMessage;
    private Long countNewMessages;

    public Long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public List<String> getLoginParticipants() {
        return loginParticipants;
    }

    public void setLoginParticipants(List<String> loginParticipants) {
        this.loginParticipants = loginParticipants;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getCountNewMessages() {
        return countNewMessages;
    }

    public void setCountNewMessages(Long countNewMessages) {
        this.countNewMessages = countNewMessages;
    }
}
