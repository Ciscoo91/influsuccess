package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.SocialNetwork;
import com.ffu.repository.SocialNetworkRepository;

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
 * Integration tests for the {@link SocialNetworkResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SocialNetworkResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocialNetworkMockMvc;

    private SocialNetwork socialNetwork;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialNetwork createEntity(EntityManager em) {
        SocialNetwork socialNetwork = new SocialNetwork()
            .name(DEFAULT_NAME);
        return socialNetwork;
    }

    @BeforeEach
    public void initTest() {
        socialNetwork = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocialNetwork() throws Exception {
        int databaseSizeBeforeCreate = socialNetworkRepository.findAll().size();
        // Create the SocialNetwork
        restSocialNetworkMockMvc.perform(post("/api/social-networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetwork)))
            .andExpect(status().isCreated());

        // Validate the SocialNetwork in the database
        List<SocialNetwork> socialNetworkList = socialNetworkRepository.findAll();
        assertThat(socialNetworkList).hasSize(databaseSizeBeforeCreate + 1);
        SocialNetwork testSocialNetwork = socialNetworkList.get(socialNetworkList.size() - 1);
        assertThat(testSocialNetwork.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createSocialNetworkWithExistingName() throws Exception {
        socialNetwork = socialNetworkRepository.saveAndFlush(socialNetwork);

        int databaseSizeBeforeCreate = socialNetworkRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocialNetworkMockMvc.perform(post("/api/social-networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetwork)))
            .andExpect(status().isBadRequest());

        // Validate the SocialNetwork in the database
        List<SocialNetwork> socialNetworkList = socialNetworkRepository.findAll();
        assertThat(socialNetworkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = socialNetworkRepository.findAll().size();
        // set the field null
        socialNetwork.setName(null);

        // Create the SocialNetwork, which fails.


        restSocialNetworkMockMvc.perform(post("/api/social-networks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetwork)))
            .andExpect(status().isBadRequest());

        List<SocialNetwork> socialNetworkList = socialNetworkRepository.findAll();
        assertThat(socialNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSocialNetworks() throws Exception {
        // Initialize the database
        socialNetworkRepository.saveAndFlush(socialNetwork);

        // Get all the socialNetworkList
        restSocialNetworkMockMvc.perform(get("/api/social-networks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getSocialNetwork() throws Exception {
        // Initialize the database
        socialNetworkRepository.saveAndFlush(socialNetwork);

        // Get the socialNetwork
        restSocialNetworkMockMvc.perform(get("/api/social-networks/{id}", socialNetwork.getName()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingSocialNetwork() throws Exception {
        // Get the socialNetwork
        restSocialNetworkMockMvc.perform(get("/api/social-networks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deleteSocialNetwork() throws Exception {
        // Initialize the database
        socialNetworkRepository.saveAndFlush(socialNetwork);

        int databaseSizeBeforeDelete = socialNetworkRepository.findAll().size();

        // Delete the socialNetwork
        restSocialNetworkMockMvc.perform(delete("/api/social-networks/{id}", socialNetwork.getName())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocialNetwork> socialNetworkList = socialNetworkRepository.findAll();
        assertThat(socialNetworkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
