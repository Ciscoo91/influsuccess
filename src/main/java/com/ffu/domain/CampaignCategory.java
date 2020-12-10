package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ffu.domain.enumeration.CampaignCategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Set;

/**
 * A CampaignCategory.
 */
@Entity
@Table(name = "campaign_category")
public class CampaignCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @Id
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private CampaignCategoryEnum name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "influencer_category",
        joinColumns = {@JoinColumn(name = "category_name", referencedColumnName = "name")},
        inverseJoinColumns = {@JoinColumn(name = "influencer_id", referencedColumnName = "id")})
    private Set<Influencer> influencers;

    @ManyToOne
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public CampaignCategoryEnum getName() {
        return name;
    }

    public CampaignCategory name(CampaignCategoryEnum name) {
        this.name = name;
        return this;
    }

    public void setName(CampaignCategoryEnum name) {
        this.name = name;
    }

    public Set<Influencer> getInfluencers() {
        return influencers;
    }

    public CampaignCategory Influencers(Set<Influencer> influencers) {
        this.influencers = influencers;
        return this;
    }

    public void setInfluencers(Set<Influencer> influencers) {
        this.influencers = influencers;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignCategory campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignCategory)) {
            return false;
        }
        return name != null && name.equals(((CampaignCategory) o).name);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampaignCategory{" +
            ", name='" + getName() + "'" +
            "}";
    }
}
