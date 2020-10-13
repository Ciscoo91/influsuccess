package com.ffu.service.dto;

import com.ffu.domain.enumeration.CampaignStatus;
import com.ffu.domain.enumeration.LangKey;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

public class CampaignDTO {

    private Long id;
    private LangKey langKey;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private CampaignStatus status;
    private Long minFollowers;
    private Long maxFollowers;
    private String targetCountries;

    @NotBlank
    private Set<SocialNetworkDTO> socialNetworks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LangKey getLangKey() {
        return langKey;
    }

    public void setLangKey(LangKey langKey) {
        this.langKey = langKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public Long getMinFollowers() {
        return minFollowers;
    }

    public void setMinFollowers(Long minFollowers) {
        this.minFollowers = minFollowers;
    }

    public Long getMaxFollowers() {
        return maxFollowers;
    }

    public void setMaxFollowers(Long maxFollowers) {
        this.maxFollowers = maxFollowers;
    }

    public String getTargetCountries() {
        return targetCountries;
    }

    public void setTargetCountries(String targetCountries) {
        this.targetCountries = targetCountries;
    }

    public Set<SocialNetworkDTO> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Set<SocialNetworkDTO> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignDTO that = (CampaignDTO) o;
        return Objects.equals(id, that.id) &&
            langKey == that.langKey &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            status == that.status &&
            Objects.equals(minFollowers, that.minFollowers) &&
            Objects.equals(maxFollowers, that.maxFollowers) &&
            Objects.equals(targetCountries, that.targetCountries) &&
            Objects.equals(socialNetworks, that.socialNetworks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, langKey, title, description, status, minFollowers, maxFollowers, targetCountries, socialNetworks);
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
            "id=" + id +
            ", langKey=" + langKey +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", status=" + status +
            ", minFollowers=" + minFollowers +
            ", maxFollowers=" + maxFollowers +
            ", targetCountries='" + targetCountries + '\'' +
            ", socialNetworks=" + socialNetworks +
            '}';
    }
}
