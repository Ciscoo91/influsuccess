package com.ffu.web.rest;

import com.ffu.InfluSuccessApp;
import com.ffu.domain.SocialNetworkLink;
import com.ffu.repository.SocialNetworkLinkRepository;
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
 * Integration tests for the {@link SocialNetworkLinkResource} REST controller.
 */
@SpringBootTest(classes = InfluSuccessApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SocialNetworkLinkResourceIT {

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    @Autowired
    private SocialNetworkLinkRepository socialNetworkLinkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocialNetworkLinkMockMvc;

    private SocialNetworkLink socialNetworkLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialNetworkLink createEntity(EntityManager em) {
        SocialNetworkLink socialNetworkLink = new SocialNetworkLink()
            .link(DEFAULT_LINK);
        return socialNetworkLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialNetworkLink createUpdatedEntity(EntityManager em) {
        SocialNetworkLink socialNetworkLink = new SocialNetworkLink()
            .link(UPDATED_LINK);
        return socialNetworkLink;
    }

    @BeforeEach
    public void initTest() {
        socialNetworkLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocialNetworkLink() throws Exception {
        int databaseSizeBeforeCreate = socialNetworkLinkRepository.findAll().size();
        // Create the SocialNetworkLink
        restSocialNetworkLinkMockMvc.perform(post("/api/social-network-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetworkLink)))
            .andExpect(status().isCreated());

        // Validate the SocialNetworkLink in the database
        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeCreate + 1);
        SocialNetworkLink testSocialNetworkLink = socialNetworkLinkList.get(socialNetworkLinkList.size() - 1);
        assertThat(testSocialNetworkLink.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    public void createSocialNetworkLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = socialNetworkLinkRepository.findAll().size();

        // Create the SocialNetworkLink with an existing ID
        socialNetworkLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocialNetworkLinkMockMvc.perform(post("/api/social-network-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetworkLink)))
            .andExpect(status().isBadRequest());

        // Validate the SocialNetworkLink in the database
        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = socialNetworkLinkRepository.findAll().size();
        // set the field null
        socialNetworkLink.setLink(null);

        // Create the SocialNetworkLink, which fails.


        restSocialNetworkLinkMockMvc.perform(post("/api/social-network-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetworkLink)))
            .andExpect(status().isBadRequest());

        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSocialNetworkLinks() throws Exception {
        // Initialize the database
        socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);

        // Get all the socialNetworkLinkList
        restSocialNetworkLinkMockMvc.perform(get("/api/social-network-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socialNetworkLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
    }

    @Test
    @Transactional
    public void getSocialNetworkLink() throws Exception {
        // Initialize the database
        socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);

        // Get the socialNetworkLink
        restSocialNetworkLinkMockMvc.perform(get("/api/social-network-links/{id}", socialNetworkLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(socialNetworkLink.getId().intValue()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
    }
    @Test
    @Transactional
    public void getNonExistingSocialNetworkLink() throws Exception {
        // Get the socialNetworkLink
        restSocialNetworkLinkMockMvc.perform(get("/api/social-network-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocialNetworkLink() throws Exception {
        // Initialize the database
        socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);

        int databaseSizeBeforeUpdate = socialNetworkLinkRepository.findAll().size();

        // Update the socialNetworkLink
        SocialNetworkLink updatedSocialNetworkLink = socialNetworkLinkRepository.findById(socialNetworkLink.getId()).get();
        // Disconnect from session so that the updates on updatedSocialNetworkLink are not directly saved in db
        em.detach(updatedSocialNetworkLink);
        updatedSocialNetworkLink
            .link(UPDATED_LINK);

        restSocialNetworkLinkMockMvc.perform(put("/api/social-network-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSocialNetworkLink)))
            .andExpect(status().isOk());

        // Validate the SocialNetworkLink in the database
        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeUpdate);
        SocialNetworkLink testSocialNetworkLink = socialNetworkLinkList.get(socialNetworkLinkList.size() - 1);
        assertThat(testSocialNetworkLink.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    public void updateNonExistingSocialNetworkLink() throws Exception {
        int databaseSizeBeforeUpdate = socialNetworkLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocialNetworkLinkMockMvc.perform(put("/api/social-network-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(socialNetworkLink)))
            .andExpect(status().isBadRequest());

        // Validate the SocialNetworkLink in the database
        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSocialNetworkLink() throws Exception {
        // Initialize the database
        socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);

        int databaseSizeBeforeDelete = socialNetworkLinkRepository.findAll().size();

        // Delete the socialNetworkLink
        restSocialNetworkLinkMockMvc.perform(delete("/api/social-network-links/{id}", socialNetworkLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocialNetworkLink> socialNetworkLinkList = socialNetworkLinkRepository.findAll();
        assertThat(socialNetworkLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
