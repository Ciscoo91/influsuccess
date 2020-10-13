package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SocialNetworkDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private CampaignDTO campaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialNetworkDTO that = (SocialNetworkDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(campaign, that.campaign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, campaign);
    }

    @Override
    public String toString() {
        return "SocialNetworkDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", campaign=" + campaign +
            '}';
    }
}
