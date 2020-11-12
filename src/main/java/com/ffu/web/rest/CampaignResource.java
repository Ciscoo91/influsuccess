package com.ffu.web.rest;

import com.ffu.domain.Campaign;
import com.ffu.service.CampaignService;
import com.ffu.service.dto.CampaignDTO;
import com.ffu.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    private static final String ENTITY_NAME = "campaignDTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignService campaignService;

    public CampaignResource(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    /**
     * {@code POST  /campaigns} : Create a new campaignDTO.
     *
     * @param campaignDTO the campaignDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignDTO, or with status {@code 400 (Bad Request)} if the campaignDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaigns")
    public ResponseEntity<CampaignDTO> createCampaign(@Valid @RequestBody CampaignDTO campaignDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignDTO : {}", campaignDTO);
        if (campaignDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignDTO result = campaignService.save(campaignDTO);
        return ResponseEntity.created(new URI("/api/campaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaigns} : Updates an existing campaignDTO.
     *
     * @param campaignDTO the campaignDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignDTO,
     * or with status {@code 400 (Bad Request)} if the campaignDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaigns")
    public ResponseEntity<CampaignDTO> updateCampaign(@Valid @RequestBody CampaignDTO campaignDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignDTO : {}", campaignDTO);
        if (campaignDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignDTO result = campaignService.save(campaignDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campaignDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaigns} : get all the campaigns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaigns in body.
     */
    @GetMapping("/campaigns")
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
        log.debug("REST request to get all Campaigns");
        List<CampaignDTO> result = campaignService.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /campaigns/opened} : get all opened the campaigns.
     * @return
     */
    @GetMapping("/campaigns/opened")
    public ResponseEntity<List<CampaignDTO>> getOpenedCampaigns(){
        log.debug("REST request to get all opened Campaigns");
        List<CampaignDTO> resutl = campaignService.getOpenedCampaigns();
        return new ResponseEntity<>(resutl, HttpStatus.OK);
    }
    /**
     * {@code GET  /campaigns/:id} : get the "id" campaignDTO.
     *
     * @param id the id of the campaignDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaigns/{id}")
    public ResponseEntity<CampaignDTO> getCampaign(@PathVariable Long id) {
        log.debug("REST request to get CampaignDTO : {}", id);
        Optional<CampaignDTO> campaignDTO = campaignService.findById(id);
        return ResponseUtil.wrapOrNotFound(campaignDTO);
    }

    /**
     * {@code DELETE  /campaigns/:id} : delete the "id" campaignDTO.
     *
     * @param id the id of the campaignDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        log.debug("REST request to delete CampaignDTO : {}", id);
        campaignService.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
