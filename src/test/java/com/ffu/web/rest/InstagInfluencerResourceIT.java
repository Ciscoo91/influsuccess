package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.InstagInfluencer;
import com.ffu.repository.InstagInfluencerRepository;

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
 * Integration tests for the {@link InstagInfluencerResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InstagInfluencerResourceIT {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private InstagInfluencerRepository instagInfluencerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstagInfluencerMockMvc;

    private InstagInfluencer instagInfluencer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstagInfluencer createEntity(EntityManager em) {
        InstagInfluencer instagInfluencer = new InstagInfluencer()
            .url(DEFAULT_URL);
        return instagInfluencer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstagInfluencer createUpdatedEntity(EntityManager em) {
        InstagInfluencer instagInfluencer = new InstagInfluencer()
            .url(UPDATED_URL);
        return instagInfluencer;
    }

    @BeforeEach
    public void initTest() {
        instagInfluencer = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstagInfluencer() throws Exception {
        int databaseSizeBeforeCreate = instagInfluencerRepository.findAll().size();
        // Create the InstagInfluencer
        restInstagInfluencerMockMvc.perform(post("/api/instag-influencers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(instagInfluencer)))
            .andExpect(status().isCreated());

        // Validate the InstagInfluencer in the database
        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeCreate + 1);
        InstagInfluencer testInstagInfluencer = instagInfluencerList.get(instagInfluencerList.size() - 1);
        assertThat(testInstagInfluencer.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createInstagInfluencerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instagInfluencerRepository.findAll().size();

        // Create the InstagInfluencer with an existing ID
        instagInfluencer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstagInfluencerMockMvc.perform(post("/api/instag-influencers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(instagInfluencer)))
            .andExpect(status().isBadRequest());

        // Validate the InstagInfluencer in the database
        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = instagInfluencerRepository.findAll().size();
        // set the field null
        instagInfluencer.setUrl(null);

        // Create the InstagInfluencer, which fails.


        restInstagInfluencerMockMvc.perform(post("/api/instag-influencers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(instagInfluencer)))
            .andExpect(status().isBadRequest());

        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstagInfluencers() throws Exception {
        // Initialize the database
        instagInfluencerRepository.saveAndFlush(instagInfluencer);

        // Get all the instagInfluencerList
        restInstagInfluencerMockMvc.perform(get("/api/instag-influencers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instagInfluencer.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    @Transactional
    public void getInstagInfluencer() throws Exception {
        // Initialize the database
        instagInfluencerRepository.saveAndFlush(instagInfluencer);

        // Get the instagInfluencer
        restInstagInfluencerMockMvc.perform(get("/api/instag-influencers/{id}", instagInfluencer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(instagInfluencer.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    @Transactional
    public void getNonExistingInstagInfluencer() throws Exception {
        // Get the instagInfluencer
        restInstagInfluencerMockMvc.perform(get("/api/instag-influencers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstagInfluencer() throws Exception {
        // Initialize the database
        instagInfluencerRepository.saveAndFlush(instagInfluencer);

        int databaseSizeBeforeUpdate = instagInfluencerRepository.findAll().size();

        // Update the instagInfluencer
        InstagInfluencer updatedInstagInfluencer = instagInfluencerRepository.findById(instagInfluencer.getId()).get();
        // Disconnect from session so that the updates on updatedInstagInfluencer are not directly saved in db
        em.detach(updatedInstagInfluencer);
        updatedInstagInfluencer
            .url(UPDATED_URL);

        restInstagInfluencerMockMvc.perform(put("/api/instag-influencers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstagInfluencer)))
            .andExpect(status().isOk());

        // Validate the InstagInfluencer in the database
        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeUpdate);
        InstagInfluencer testInstagInfluencer = instagInfluencerList.get(instagInfluencerList.size() - 1);
        assertThat(testInstagInfluencer.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingInstagInfluencer() throws Exception {
        int databaseSizeBeforeUpdate = instagInfluencerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstagInfluencerMockMvc.perform(put("/api/instag-influencers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(instagInfluencer)))
            .andExpect(status().isBadRequest());

        // Validate the InstagInfluencer in the database
        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstagInfluencer() throws Exception {
        // Initialize the database
        instagInfluencerRepository.saveAndFlush(instagInfluencer);

        int databaseSizeBeforeDelete = instagInfluencerRepository.findAll().size();

        // Delete the instagInfluencer
        restInstagInfluencerMockMvc.perform(delete("/api/instag-influencers/{id}", instagInfluencer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstagInfluencer> instagInfluencerList = instagInfluencerRepository.findAll();
        assertThat(instagInfluencerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
