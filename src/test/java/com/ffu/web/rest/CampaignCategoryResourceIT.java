package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.CampaignCategory;
import com.ffu.repository.CampaignCategoryRepository;
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
 * Integration tests for the {@link CampaignCategoryResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CampaignCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private CampaignCategoryRepository campaignCategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampaignCategoryMockMvc;

    private CampaignCategory campaignCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignCategory createEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory()
            .name(DEFAULT_NAME);
        return campaignCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignCategory createUpdatedEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory()
            .name(UPDATED_NAME);
        return campaignCategory;
    }

    @BeforeEach
    public void initTest() {
        campaignCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignCategory() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();
        // Create the CampaignCategory
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategory)))
            .andExpect(status().isCreated());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
        assertThat(testCampaignCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createCampaignCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory with an existing ID
        campaignCategory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategory)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = campaignCategoryRepository.findAll().size();
        // set the field null
        campaignCategory.setName(null);

        // Create the CampaignCategory, which fails.


        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategory)))
            .andExpect(status().isBadRequest());

        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCampaignCategories() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get all the campaignCategoryList
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", campaignCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campaignCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCampaignCategory() throws Exception {
        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // Update the campaignCategory
        CampaignCategory updatedCampaignCategory = campaignCategoryRepository.findById(campaignCategory.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignCategory are not directly saved in db
        em.detach(updatedCampaignCategory);
        updatedCampaignCategory
            .name(UPDATED_NAME);

        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCampaignCategory)))
            .andExpect(status().isOk());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
        assertThat(testCampaignCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignCategory() throws Exception {
        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategory)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeDelete = campaignCategoryRepository.findAll().size();

        // Delete the campaignCategory
        restCampaignCategoryMockMvc.perform(delete("/api/campaign-categories/{id}", campaignCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
