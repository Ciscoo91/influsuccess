package com.ffu.web.rest;

import com.ffu.domain.CampaignCategory;
import com.ffu.repository.CampaignCategoryRepository;
import com.ffu.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ffu.domain.CampaignCategory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CampaignCategoryResource {

    private final Logger log = LoggerFactory.getLogger(CampaignCategoryResource.class);

    private static final String ENTITY_NAME = "campaignCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignCategoryRepository campaignCategoryRepository;

    public CampaignCategoryResource(CampaignCategoryRepository campaignCategoryRepository) {
        this.campaignCategoryRepository = campaignCategoryRepository;
    }

    /**
     * {@code POST  /campaign-categories} : Create a new campaignCategory.
     *
     * @param campaignCategory the campaignCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignCategory, or with status {@code 400 (Bad Request)} if the campaignCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-categories")
    public ResponseEntity<CampaignCategory> createCampaignCategory(@Valid @RequestBody CampaignCategory campaignCategory) throws URISyntaxException {
        log.debug("REST request to save CampaignCategory : {}", campaignCategory);
        if (campaignCategory.getId() != null) {
            throw new BadRequestAlertException("A new campaignCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignCategory result = campaignCategoryRepository.save(campaignCategory);
        return ResponseEntity.created(new URI("/api/campaign-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-categories} : Updates an existing campaignCategory.
     *
     * @param campaignCategory the campaignCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignCategory,
     * or with status {@code 400 (Bad Request)} if the campaignCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-categories")
    public ResponseEntity<CampaignCategory> updateCampaignCategory(@Valid @RequestBody CampaignCategory campaignCategory) throws URISyntaxException {
        log.debug("REST request to update CampaignCategory : {}", campaignCategory);
        if (campaignCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignCategory result = campaignCategoryRepository.save(campaignCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campaignCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-categories} : get all the campaignCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignCategories in body.
     */
    @GetMapping("/campaign-categories")
    public List<CampaignCategory> getAllCampaignCategories() {
        log.debug("REST request to get all CampaignCategories");
        return campaignCategoryRepository.findAll();
    }

    /**
     * {@code GET  /campaign-categories/:id} : get the "id" campaignCategory.
     *
     * @param id the id of the campaignCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-categories/{id}")
    public ResponseEntity<CampaignCategory> getCampaignCategory(@PathVariable Long id) {
        log.debug("REST request to get CampaignCategory : {}", id);
        Optional<CampaignCategory> campaignCategory = campaignCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(campaignCategory);
    }

    /**
     * {@code DELETE  /campaign-categories/:id} : delete the "id" campaignCategory.
     *
     * @param id the id of the campaignCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-categories/{id}")
    public ResponseEntity<Void> deleteCampaignCategory(@PathVariable Long id) {
        log.debug("REST request to delete CampaignCategory : {}", id);
        campaignCategoryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
