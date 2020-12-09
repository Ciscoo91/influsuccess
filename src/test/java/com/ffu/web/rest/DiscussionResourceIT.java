package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.Campaign;
import com.ffu.domain.Discussion;
import com.ffu.domain.User;
import com.ffu.repository.DiscussionRepository;

import com.ffu.service.dto.DiscussionDTO;
import com.ffu.service.mapper.DiscussionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DiscussionResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DiscussionResourceIT {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiscussionMockMvc;

    private Discussion discussion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Discussion createEntity(EntityManager em) {
        Discussion discussion = new Discussion();
        return discussion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Discussion createUpdatedEntity(EntityManager em) {
        Discussion discussion = new Discussion();
        return discussion;
    }

    @BeforeEach
    public void initTest() {
        discussion = createEntity(em);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);

        Campaign campaign = CampaignResourceIT.createEntity(em);
        campaign.setUser(user);
        em.persist(campaign);

        discussion.setParticipants(new HashSet<User>(Arrays.asList(user)));
        discussion.setCampaign(campaign);
    }

    @Test
    @Transactional
    public void createDiscussion() throws Exception {
        int databaseSizeBeforeCreate = discussionRepository.findAll().size();
        DiscussionDTO discussionDTO = discussionMapper.toDTO(discussion);
        // Create the Discussion
        restDiscussionMockMvc.perform(post("/api/discussions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(discussionDTO)))
            .andExpect(status().isCreated());

        // Validate the Discussion in the database
        List<Discussion> discussionList = discussionRepository.findAll();
        assertThat(discussionList).hasSize(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    public void createDiscussionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = discussionRepository.findAll().size();

        // Create the Discussion with an existing ID
        discussion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiscussionMockMvc.perform(post("/api/discussions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(discussionMapper.toDTO(discussion))))
            .andExpect(status().isBadRequest());

        // Validate the Discussion in the database
        List<Discussion> discussionList = discussionRepository.findAll();
        assertThat(discussionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDiscussions() throws Exception {
        // Initialize the database
        discussionRepository.saveAndFlush(discussion);

        // Get all the discussionList
        restDiscussionMockMvc.perform(get("/api/discussions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(discussion.getId().intValue())));
    }

    @Test
    @Transactional
    public void getDiscussion() throws Exception {
        // Initialize the database
        discussionRepository.saveAndFlush(discussion);

        // Get the discussion
        restDiscussionMockMvc.perform(get("/api/discussions/{id}", discussion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(discussion.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDiscussion() throws Exception {
        // Get the discussion
        restDiscussionMockMvc.perform(get("/api/discussions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiscussion() throws Exception {
        // Initialize the database
        discussionRepository.saveAndFlush(discussion);

        int databaseSizeBeforeUpdate = discussionRepository.findAll().size();

        // Update the discussion
        Discussion updatedDiscussion = discussionRepository.findById(discussion.getId()).get();
        // Disconnect from session so that the updates on updatedDiscussion are not directly saved in db
        em.detach(updatedDiscussion);

        DiscussionDTO updatedDiscussionDTO = discussionMapper.toDTO(updatedDiscussion);

        restDiscussionMockMvc.perform(put("/api/discussions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiscussionDTO)))
            .andExpect(status().isOk());

        // Validate the Discussion in the database
        List<Discussion> discussionList = discussionRepository.findAll();
        assertThat(discussionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void updateNonExistingDiscussion() throws Exception {
        int databaseSizeBeforeUpdate = discussionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiscussionMockMvc.perform(put("/api/discussions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(discussionMapper.toDTO(discussion))))
            .andExpect(status().isBadRequest());

        // Validate the Discussion in the database
        List<Discussion> discussionList = discussionRepository.findAll();
        assertThat(discussionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiscussion() throws Exception {
        // Initialize the database
        discussionRepository.saveAndFlush(discussion);

        int databaseSizeBeforeDelete = discussionRepository.findAll().size();

        // Delete the discussion
        restDiscussionMockMvc.perform(delete("/api/discussions/{id}", discussion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Discussion> discussionList = discussionRepository.findAll();
        assertThat(discussionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
