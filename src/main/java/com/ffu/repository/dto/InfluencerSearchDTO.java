package com.ffu.repository.dto;


public class InfluencerSearchDTO {
    private String username;

    private String socialNetworkName;

    private String campaignCategoryEnum;

    private String countryCode;

    private Long totalFollowerMin;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSocialNetworkName() {
        return this.socialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

    public String getCampaignCategoryEnum() {
        return this.campaignCategoryEnum;
    }

    public void setCampaignCategoryEnum(String campaignCategoryEnum) {
        this.campaignCategoryEnum = campaignCategoryEnum;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getTotalFollowerMin() {
        return this.totalFollowerMin;
    }

    public void setTotalFollowerMin(Long totalFollowerMin) {
        this.totalFollowerMin = totalFollowerMin;
    }
}
