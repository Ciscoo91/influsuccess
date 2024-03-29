package com.ffu.domain;

import com.ffu.domain.enumeration.SocialNetworkEnum;


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
    @Enumerated(EnumType.STRING)
    private SocialNetworkEnum name;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    public SocialNetworkEnum getName() {
        return name;
    }

    public SocialNetwork name(SocialNetworkEnum name) {
        this.name = name;
        return this;
    }

    public void setName(SocialNetworkEnum name) {
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
