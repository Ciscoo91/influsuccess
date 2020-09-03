package com.ffu.web.rest;

import com.ffu.domain.Campaign;
import com.ffu.repository.CampaignRepository;
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
 * REST controller for managing {@link com.ffu.domain.Campaign}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CampaignResource {

    private final Logger log = LoggerFactory.getLogger(CampaignResource.class);

    private static final String ENTITY_NAME = "campaign";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignRepository campaignRepository;

    public CampaignResource(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    /**
     * {@code POST  /campaigns} : Create a new campaign.
     *
     * @param campaign the campaign to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaign, or with status {@code 400 (Bad Request)} if the campaign has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaigns")
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) throws URISyntaxException {
        log.debug("REST request to save Campaign : {}", campaign);
        if (campaign.getId() != null) {
            throw new BadRequestAlertException("A new campaign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Campaign result = campaignRepository.save(campaign);
        return ResponseEntity.created(new URI("/api/campaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaigns} : Updates an existing campaign.
     *
     * @param campaign the campaign to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaign,
     * or with status {@code 400 (Bad Request)} if the campaign is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaign couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaigns")
    public ResponseEntity<Campaign> updateCampaign(@Valid @RequestBody Campaign campaign) throws URISyntaxException {
        log.debug("REST request to update Campaign : {}", campaign);
        if (campaign.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Campaign result = campaignRepository.save(campaign);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campaign.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaigns} : get all the campaigns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaigns in body.
     */
    @GetMapping("/campaigns")
    public List<Campaign> getAllCampaigns() {
        log.debug("REST request to get all Campaigns");
        return campaignRepository.findAll();
    }

    /**
     * {@code GET  /campaigns/:id} : get the "id" campaign.
     *
     * @param id the id of the campaign to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaign, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> getCampaign(@PathVariable Long id) {
        log.debug("REST request to get Campaign : {}", id);
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(campaign);
    }

    /**
     * {@code DELETE  /campaigns/:id} : delete the "id" campaign.
     *
     * @param id the id of the campaign to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        log.debug("REST request to delete Campaign : {}", id);
        campaignRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
