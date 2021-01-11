package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.InfluencerInfo;
import com.ffu.repository.InfluencerInfoRepository;

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
 * Integration tests for the {@link InfluencerInfoResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfluencerInfoResourceIT {

    @Autowired
    private InfluencerInfoRepository influencerInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfluencerInfoMockMvc;

    private InfluencerInfo influencerInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfluencerInfo createEntity(EntityManager em) {
        InfluencerInfo influencerInfo = new InfluencerInfo();
        return influencerInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfluencerInfo createUpdatedEntity(EntityManager em) {
        InfluencerInfo influencerInfo = new InfluencerInfo();
        return influencerInfo;
    }

    @BeforeEach
    public void initTest() {
        influencerInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfluencerInfo() throws Exception {
        int databaseSizeBeforeCreate = influencerInfoRepository.findAll().size();
        // Create the InfluencerInfo
        restInfluencerInfoMockMvc.perform(post("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencerInfo)))
            .andExpect(status().isCreated());

        // Validate the InfluencerInfo in the database
        List<InfluencerInfo> influencerInfoList = influencerInfoRepository.findAll();
        assertThat(influencerInfoList).hasSize(databaseSizeBeforeCreate + 1);
        InfluencerInfo testInfluencerInfo = influencerInfoList.get(influencerInfoList.size() - 1);
    }

    @Test
    @Transactional
    public void createInfluencerInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = influencerInfoRepository.findAll().size();

        // Create the InfluencerInfo with an existing ID
        influencerInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfluencerInfoMockMvc.perform(post("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencerInfo)))
            .andExpect(status().isBadRequest());

        // Validate the InfluencerInfo in the database
        List<InfluencerInfo> influencerInfoList = influencerInfoRepository.findAll();
        assertThat(influencerInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInfluencerInfos() throws Exception {
        // Initialize the database
        influencerInfoRepository.saveAndFlush(influencerInfo);

        // Get all the influencerInfoList
        restInfluencerInfoMockMvc.perform(get("/api/influencer-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(influencerInfo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getInfluencerInfo() throws Exception {
        // Initialize the database
        influencerInfoRepository.saveAndFlush(influencerInfo);

        // Get the influencerInfo
        restInfluencerInfoMockMvc.perform(get("/api/influencer-infos/{id}", influencerInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(influencerInfo.getId().intValue()));
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
        influencerInfoRepository.saveAndFlush(influencerInfo);

        int databaseSizeBeforeUpdate = influencerInfoRepository.findAll().size();

        // Update the influencerInfo
        InfluencerInfo updatedInfluencerInfo = influencerInfoRepository.findById(influencerInfo.getId()).get();
        // Disconnect from session so that the updates on updatedInfluencerInfo are not directly saved in db
        em.detach(updatedInfluencerInfo);

        restInfluencerInfoMockMvc.perform(put("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInfluencerInfo)))
            .andExpect(status().isOk());

        // Validate the InfluencerInfo in the database
        List<InfluencerInfo> influencerInfoList = influencerInfoRepository.findAll();
        assertThat(influencerInfoList).hasSize(databaseSizeBeforeUpdate);
        InfluencerInfo testInfluencerInfo = influencerInfoList.get(influencerInfoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingInfluencerInfo() throws Exception {
        int databaseSizeBeforeUpdate = influencerInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfluencerInfoMockMvc.perform(put("/api/influencer-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(influencerInfo)))
            .andExpect(status().isBadRequest());

        // Validate the InfluencerInfo in the database
        List<InfluencerInfo> influencerInfoList = influencerInfoRepository.findAll();
        assertThat(influencerInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfluencerInfo() throws Exception {
        // Initialize the database
        influencerInfoRepository.saveAndFlush(influencerInfo);

        int databaseSizeBeforeDelete = influencerInfoRepository.findAll().size();

        // Delete the influencerInfo
        restInfluencerInfoMockMvc.perform(delete("/api/influencer-infos/{id}", influencerInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InfluencerInfo> influencerInfoList = influencerInfoRepository.findAll();
        assertThat(influencerInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
