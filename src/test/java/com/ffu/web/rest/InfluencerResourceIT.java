package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.Influencer;
import com.ffu.repository.InfluencerRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InfluencerResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfluencerResourceIT {

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfluencerInfoMockMvc;

    private Influencer influencer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Influencer createEntity(EntityManager em) {
        Influencer influencer = new Influencer();
        return influencer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Influencer createUpdatedEntity(EntityManager em) {
        Influencer influencer = new Influencer();
        return influencer;
    }

    @BeforeEach
    public void initTest() {
        influencer = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfluencerInfo() throws Exception {
        int databaseSizeBeforeCreate = influencerRepository.findAll().size();
        // Create the InfluencerInfo
        restInfluencerInfoMockMvc.perform(post("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencer)))
            .andExpect(status().isCreated());

        // Validate the InfluencerInfo in the database
        List<Influencer> influencerList = influencerRepository.findAll();
        assertThat(influencerList).hasSize(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    public void createInfluencerInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = influencerRepository.findAll().size();

        // Create the InfluencerInfo with an existing ID
        influencer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfluencerInfoMockMvc.perform(post("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencer)))
            .andExpect(status().isBadRequest());

        // Validate the InfluencerInfo in the database
        List<Influencer> influencerList = influencerRepository.findAll();
        assertThat(influencerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInfluencerInfos() throws Exception {
        // Initialize the database
        influencerRepository.saveAndFlush(influencer);

        // Get all the influencerInfoList
        restInfluencerInfoMockMvc.perform(get("/api/influencer-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(influencer.getId().intValue())));
    }

    @Test
    @Transactional
    public void getInfluencerInfo() throws Exception {
        // Initialize the database
        influencerRepository.saveAndFlush(influencer);

        // Get the influencerInfo
        restInfluencerInfoMockMvc.perform(get("/api/influencer-infos/{id}", influencer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(influencer.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInfluencerInfo() throws Exception {
        // Get the influencerInfo
        restInfluencerInfoMockMvc.perform(get("/api/influencer-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfluencerInfo() throws Exception {
        // Initialize the database
        influencerRepository.saveAndFlush(influencer);

        int databaseSizeBeforeUpdate = influencerRepository.findAll().size();

        // Update the influencerInfo
        Influencer updatedInfluencer = influencerRepository.findById(influencer.getId()).get();
        // Disconnect from session so that the updates on updatedInfluencerInfo are not directly saved in db
        em.detach(updatedInfluencer);

        restInfluencerInfoMockMvc.perform(put("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInfluencer)))
            .andExpect(status().isOk());

        // Validate the InfluencerInfo in the database
        List<Influencer> influencerList = influencerRepository.findAll();
        assertThat(influencerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void updateNonExistingInfluencerInfo() throws Exception {
        int databaseSizeBeforeUpdate = influencerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfluencerInfoMockMvc.perform(put("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencer)))
            .andExpect(status().isBadRequest());

        // Validate the InfluencerInfo in the database
        List<Influencer> influencerList = influencerRepository.findAll();
        assertThat(influencerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfluencerInfo() throws Exception {
        // Initialize the database
        influencerRepository.saveAndFlush(influencer);

        int databaseSizeBeforeDelete = influencerRepository.findAll().size();

        // Delete the influencerInfo
        restInfluencerInfoMockMvc.perform(delete("/api/influencer-infos/{id}", influencer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Influencer> influencerList = influencerRepository.findAll();
        assertThat(influencerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
