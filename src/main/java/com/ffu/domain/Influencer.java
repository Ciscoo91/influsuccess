package com.ffu.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An Influencer.
 */
@Entity
@Table(name = "influencer")
public class Influencer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254)
    private String email;

    @OneToMany(mappedBy = "influencer", fetch = FetchType.EAGER)
    private Set<SocialNetworkLink> socialNetworkLinks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
            name = "influencer_category",
        joinColumns = {@JoinColumn(name = "influencer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "category_name", referencedColumnName = "name")})
    private Set<CampaignCategory> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "influencer_country",
        joinColumns = {@JoinColumn(name = "influencer_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "country_code", referencedColumnName = "code")})
    // jhipster-needle-entity-add-field - JHipster will add fields here
    private Set<Country> countries;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SocialNetworkLink> getSocialNetworkLinks() {
        return socialNetworkLinks;
    }

    public Influencer socialNetworkLinks(Set<SocialNetworkLink> socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
        return this;
    }

    public Influencer addSocialNetworkLinks(SocialNetworkLink socialNetworkLink) {
        this.socialNetworkLinks.add(socialNetworkLink);
        socialNetworkLink.setInfluencer(this);
        return this;
    }

    public Influencer removeSocialNetworkLinks(SocialNetworkLink socialNetworkLink) {
        this.socialNetworkLinks.remove(socialNetworkLink);
        socialNetworkLink.setInfluencer(null);
        return this;
    }

    public void setSocialNetworkLinks(Set<SocialNetworkLink> socialNetworkLinks) {
        this.socialNetworkLinks = socialNetworkLinks;
    }

    public Set<CampaignCategory> getCategories() {
        return categories;
    }

    public Influencer categories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
        return this;
    }

    public Influencer addCategories(CampaignCategory campaignCategory) {
        this.categories.add(campaignCategory);
        campaignCategory.setInfluencers(Collections.singleton(this));
        return this;
    }

    public Influencer removeCategories(CampaignCategory campaignCategory) {
        this.categories.remove(campaignCategory);
        campaignCategory.setInfluencers(null);
        return this;
    }

    public void setCategories(Set<CampaignCategory> campaignCategories) {
        this.categories = campaignCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public Set<Country> getCountries() {
        return countries;
    }

    public Influencer countries(Set<Country> countries) {
        this.countries = countries;
        return this;
    }

    public Influencer addCountries(Country country) {
        this.countries.add(country);
        country.setInfluencers(Collections.singleton(this));
        return this;
    }

    public Influencer removeCountries(Country country) {
        this.countries.remove(country);
        country.setInfluencers(null);
        return this;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Influencer)) {
            return false;
        }
        return id != null && id.equals(((Influencer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Influencer{" +
            "id=" + getId() +
            "}";
    }
}
