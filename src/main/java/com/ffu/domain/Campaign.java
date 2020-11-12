package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ffu.domain.enumeration.LangKey;

import com.ffu.domain.enumeration.CampaignStatus;

/**
 * A Campaign.
 */
@Entity
@Table(name = "campaign")
public class Campaign extends AbstractAuditingEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lang_key", nullable = false)
    private LangKey langKey;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false, length=10485760)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CampaignStatus status;

    @NotNull
    @Column(name = "min_followers", nullable = false)
    private Long minFollowers;

    @NotNull
    @Column(name = "max_followers", nullable = false)
    private Long maxFollowers;

    @Column(name = "target_countries")
    private String targetCountries;

    @OneToMany(mappedBy = "campaign")
    private Set<CampaignCategory> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "campaign_social_network",
        joinColumns = {@JoinColumn(name = "campaign_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "social_network_name", referencedColumnName = "name")})
    private Set<SocialNetwork> socialNetworks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "campaigns", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LangKey getLangKey() {
        return langKey;
    }

    public Campaign langKey(LangKey langKey) {
        this.langKey = langKey;
        return this;
    }

    public void setLangKey(LangKey langKey) {
        this.langKey = langKey;
    }

    public String getTitle() {
        return title;
    }

    public Campaign title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Campaign description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public Campaign status(CampaignStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public Long getMinFollowers() {
        return minFollowers;
    }

    public Campaign minFollowers(Long minFollowers) {
        this.minFollowers = minFollowers;
        return this;
    }

    public void setMinFollowers(Long minFollowers) {
        this.minFollowers = minFollowers;
    }

    public Long getMaxFollowers() {
        return maxFollowers;
    }

    public Campaign maxFollowers(Long maxFollowers) {
        this.maxFollowers = maxFollowers;
        return this;
    }

    public void setMaxFollowers(Long maxFollowers) {
        this.maxFollowers = maxFollowers;
    }

    public String getTargetCountries() {
        return targetCountries;
    }

    public Campaign targetCountries(String targetCountries) {
        this.targetCountries = targetCountries;
        return this;
    }

    public void setTargetCountries(String targetCountries) {
        this.targetCountries = targetCountries;
    }

    public Set<CampaignCategory> getCategories() {
        return categories;
    }

    public Campaign categories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
        return this;
    }

    public Campaign addCategories(CampaignCategory campaignCategory) {
        this.categories.add(campaignCategory);
        campaignCategory.setCampaign(this);
        return this;
    }

    public Campaign removeCategories(CampaignCategory campaignCategory) {
        this.categories.remove(campaignCategory);
        campaignCategory.setCampaign(null);
        return this;
    }

    public void setCategories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
    }

    public Set<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public Campaign socialNetworks(Set<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
        return this;
    }


    public void setSocialNetworks(Set<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public User getUser() {
        return user;
    }

    public Campaign user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campaign)) {
            return false;
        }
        return id != null && id.equals(((Campaign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Campaign{" +
            "id=" + getId() +
            ", langKey='" + getLangKey() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", minFollowers=" + getMinFollowers() +
            ", maxFollowers=" + getMaxFollowers() +
            ", targetCountries='" + getTargetCountries() + "'" +
            "}";
    }
}
