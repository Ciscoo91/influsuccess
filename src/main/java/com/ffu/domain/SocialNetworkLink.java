package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SocialNetworkLink.
 */
@Entity
@Table(name = "social_network_link")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SocialNetworkLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "link", nullable = false)
    private String link;

    @OneToOne
    @JoinColumn(unique = true)
    private SocialNetwork socialNetwork;

    @ManyToOne
    @JsonIgnoreProperties(value = "socialNetworkLinks", allowSetters = true)
    private InfluencerInfo influencerInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public SocialNetworkLink link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public SocialNetworkLink socialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        return this;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public InfluencerInfo getInfluencerInfo() {
        return influencerInfo;
    }

    public SocialNetworkLink influencerInfo(InfluencerInfo influencerInfo) {
        this.influencerInfo = influencerInfo;
        return this;
    }

    public void setInfluencerInfo(InfluencerInfo influencerInfo) {
        this.influencerInfo = influencerInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialNetworkLink)) {
            return false;
        }
        return id != null && id.equals(((SocialNetworkLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocialNetworkLink{" +
            "id=" + getId() +
            ", link='" + getLink() + "'" +
            "}";
    }
}
