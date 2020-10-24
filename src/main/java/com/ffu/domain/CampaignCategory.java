package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CampaignCategory.
 */
@Entity
@Table(name = "campaign_category")
public class CampaignCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private InfluencerInfo influencerInfo;

    @ManyToOne
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CampaignCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InfluencerInfo getInfluencerInfo() {
        return influencerInfo;
    }

    public CampaignCategory influencerInfo(InfluencerInfo influencerInfo) {
        this.influencerInfo = influencerInfo;
        return this;
    }

    public void setInfluencerInfo(InfluencerInfo influencerInfo) {
        this.influencerInfo = influencerInfo;
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
        return id != null && id.equals(((CampaignCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampaignCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
