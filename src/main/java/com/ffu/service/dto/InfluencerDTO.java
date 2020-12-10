package com.ffu.service.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.ffu.domain.CampaignCategory;
import com.ffu.domain.Country;

public class InfluencerDTO {

    private Long id;

   @NotBlank
    private String username;
    
    private String email;

    private Long totalFollowers;

    private BigDecimal rateEngagement;

    private Set<SocialNetworkLinkDTO> socialNetworkLinks;

    private Set<CampaignCategory> campaignCategories;

    private Set<Country> countries;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalFollowers() {
        return this.totalFollowers;
    }

    public void setTotalFollowers(Long totalFollowers) {
        this.totalFollowers = totalFollowers;
    }

    public BigDecimal getRateEngagement() {
        return this.rateEngagement;
    }

    public void setRateEngagement(BigDecimal rateEngagement) {
        this.rateEngagement = rateEngagement;
    }


    public Set<SocialNetworkLinkDTO> getSocialNetworkLinks() {
        return this.socialNetworkLinks;
    }

    public void setSocialNetworkLinks(Set<SocialNetworkLinkDTO> socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
    }

    public Set<CampaignCategory> getCampaignCategories() {
        return this.campaignCategories;
    }

    public void setCampaignCategories(Set<CampaignCategory> campaignCategories) {
        this.campaignCategories = campaignCategories;
    }

    public Set<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

}
