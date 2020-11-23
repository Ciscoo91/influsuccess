package com.ffu.service.dto;

import com.ffu.domain.CampaignCategory;
import com.ffu.domain.Country;
import com.ffu.domain.SocialNetwork;

public class ScrapperRequestDTO {

    private SocialNetwork socialNetwork;

    private CampaignCategory category;

    private Country country;

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public CampaignCategory getCategory() {
        return category;
    }

    public void setCategory(CampaignCategory category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
