package com.ffu.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;


    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String code;

    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @OneToMany
    private Set<UserExtra> userExtras;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "influencer_country",
        joinColumns = {@JoinColumn(name = "country_code", referencedColumnName = "name")},
        inverseJoinColumns = {@JoinColumn(name = "influencer_id", referencedColumnName = "id")})
    // jhipster-needle-entity-add-field - JHipster will add fields here
    private Set<Influencer> influencers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserExtra> getUserExtras() {
        return userExtras;
    }

    public void setUserExtras(Set<UserExtra> userExtras) {
        this.userExtras = userExtras;
    }

    public Set<Influencer> getInfluencers() {
        return influencers;
    }

    public void setInfluencers(Set<Influencer> influencers) {
        this.influencers = influencers;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return name != null && name.equals(((Country) o).name);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocialNetwork{" +
            ", name='" + getName() + "'" +
            "}";
    }
}
