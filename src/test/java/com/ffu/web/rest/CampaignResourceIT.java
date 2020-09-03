package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.Campaign;
import com.ffu.repository.CampaignRepository;

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
 * Integration tests for the {@link CampaignResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CampaignResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampaignMockMvc;

    private Campaign campaign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .title(DEFAULT_TITLE);
        return campaign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createUpdatedEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .title(UPDATED_TITLE);
        return campaign;
    }

    @BeforeEach
    public void initTest() {
        campaign = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaign() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();
        // Create the Campaign
        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isCreated());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate + 1);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createCampaignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();

        // Create the Campaign with an existing ID
        campaign.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = campaignRepository.findAll().size();
        // set the field null
        campaign.setTitle(null);

        // Create the Campaign, which fails.


        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isBadRequest());

        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCampaigns() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get all the campaignList
        restCampaignMockMvc.perform(get("/api/campaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }
    
    @Test
    @Transactional
    public void getCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", campaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campaign.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }
    @Test
    @Transactional
    public void getNonExistingCampaign() throws Exception {
        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // Update the campaign
        Campaign updatedCampaign = campaignRepository.findById(campaign.getId()).get();
        // Disconnect from session so that the updates on updatedCampaign are not directly saved in db
        em.detach(updatedCampaign);
        updatedCampaign
            .title(UPDATED_TITLE);

        restCampaignMockMvc.perform(put("/api/campaigns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCampaign)))
            .andExpect(status().isOk());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignMockMvc.perform(put("/api/campaigns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeDelete = campaignRepository.findAll().size();

        // Delete the campaign
        restCampaignMockMvc.perform(delete("/api/campaigns/{id}", campaign.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
