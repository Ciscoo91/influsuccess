package com.ffu.repository.dto;

import com.ffu.domain.enumeration.CampaignCategoryEnum;
import com.ffu.domain.enumeration.SocialNetworkEnum;

public class InfluencerSearchDTO {

    private String username;

    private SocialNetworkEnum socialNetworkName;

    private CampaignCategoryEnum campaignCategoryEnum;

    private String countryCode;

    private Long totalFollowerMin;



    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SocialNetworkEnum getSocialNetworkName() {
        return this.socialNetworkName;
    }

    public void setSocialNetworkName(SocialNetworkEnum socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

    public CampaignCategoryEnum getCampaignCategoryEnum() {
        return this.campaignCategoryEnum;
    }

    public void setCampaignCategoryEnum(CampaignCategoryEnum campaignCategoryEnum) {
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
