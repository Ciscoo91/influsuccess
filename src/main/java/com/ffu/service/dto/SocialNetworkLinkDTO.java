package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;

import com.ffu.domain.enumeration.SocialNetworkEnum;

public class SocialNetworkLinkDTO {
    @NotBlank
    private String link;
    
    @NotBlank
    private SocialNetworkEnum socialNetworkName;


    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SocialNetworkEnum getSocialNetworkName() {
        return this.socialNetworkName;
    }

    public void setSocialNetworkName(SocialNetworkEnum socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }
    
}
