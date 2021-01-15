package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;


public class SocialNetworkLinkDTO {
    @NotBlank
    private String link;

    @NotBlank
    private String socialNetworkName;


    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSocialNetworkName() {
        return this.socialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

}

