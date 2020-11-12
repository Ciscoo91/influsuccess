package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private Campaign compaign;

    @ManyToOne
    @JsonIgnoreProperties(value = "contacts", allowSetters = true)
    private InstagInfluencer instagInfluencer;

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

    public Contact user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campaign getCompaign() {
        return compaign;
    }

    public Contact compaign(Campaign campaign) {
        this.compaign = campaign;
        return this;
    }

    public void setCompaign(Campaign campaign) {
        this.compaign = campaign;
    }

    public InstagInfluencer getInstagInfluencer() {
        return instagInfluencer;
    }

    public Contact instagInfluencer(InstagInfluencer instagInfluencer) {
        this.instagInfluencer = instagInfluencer;
        return this;
    }

    public void setInstagInfluencer(InstagInfluencer instagInfluencer) {
        this.instagInfluencer = instagInfluencer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            "}";
    }
}
