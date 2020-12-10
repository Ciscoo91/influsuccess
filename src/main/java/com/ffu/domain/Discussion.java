package com.ffu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A Discussion.
 */
@Entity
@Table(name = "discussion")
public class Discussion extends AbstractAuditingEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "jhi_user_discussion",
        joinColumns = {@JoinColumn(name = "discussion_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> participants = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "discussion")
    private Set<Message> messages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "discussions", allowSetters = true)
    private Campaign campaign;


    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> users) {
        this.participants = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Discussion messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Discussion addMessages(Message message) {
        this.messages.add(message);
        message.setDiscussion(this);
        return this;
    }

    public Discussion removeMessages(Message message) {
        this.messages.remove(message);
        message.setDiscussion(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Campaign getCampaign() {
        return campaign;
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
        if (!(o instanceof Discussion)) {
            return false;
        }
        return id != null && id.equals(((Discussion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Discussion{" +
            "id=" + getId() +
            "}";
    }
}
