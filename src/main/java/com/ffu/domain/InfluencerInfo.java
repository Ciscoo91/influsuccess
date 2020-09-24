package com.ffu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A InfluencerInfo.
 */
@Entity
@Table(name = "influencer_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfluencerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "influencerInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SocialNetworkLink> socialNetworkLinks = new HashSet<>();

    @OneToMany(mappedBy = "influencerInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CampaignCategory> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public InfluencerInfo user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<SocialNetworkLink> getSocialNetworkLinks() {
        return socialNetworkLinks;
    }

    public InfluencerInfo socialNetworkLinks(Set<SocialNetworkLink> socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
        return this;
    }

    public InfluencerInfo addSocialNetworkLinks(SocialNetworkLink socialNetworkLink) {
        this.socialNetworkLinks.add(socialNetworkLink);
        socialNetworkLink.setInfluencerInfo(this);
        return this;
    }

    public InfluencerInfo removeSocialNetworkLinks(SocialNetworkLink socialNetworkLink) {
        this.socialNetworkLinks.remove(socialNetworkLink);
        socialNetworkLink.setInfluencerInfo(null);
        return this;
    }

    public void setSocialNetworkLinks(Set<SocialNetworkLink> socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
    }

    public Set<CampaignCategory> getCategories() {
        return categories;
    }

    public InfluencerInfo categories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
        return this;
    }

    public InfluencerInfo addCategories(CampaignCategory campaignCategory) {
        this.categories.add(campaignCategory);
        campaignCategory.setInfluencerInfo(this);
        return this;
    }

    public InfluencerInfo removeCategories(CampaignCategory campaignCategory) {
        this.categories.remove(campaignCategory);
        campaignCategory.setInfluencerInfo(null);
        return this;
    }

    public void setCategories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfluencerInfo)) {
            return false;
        }
        return id != null && id.equals(((InfluencerInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfluencerInfo{" +
            "id=" + getId() +
            "}";
    }
}
