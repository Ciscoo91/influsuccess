package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SocialNetwork.
 */
@Entity
@Table(name = "social_network")
public class SocialNetwork implements Serializable {

    private static final long serialVersionUID = 1L;


    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    public String getName() {
        return name;
    }

    public SocialNetwork name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }



    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocialNetwork)) {
            return false;
        }
        return name != null && name.equals(((SocialNetwork) o).name);
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
