package com.ffu.web.rest;

import com.ffu.domain.InstagInfluencer;
import com.ffu.repository.InstagInfluencerRepository;
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
 * REST controller for managing {@link com.ffu.domain.InstagInfluencer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InstagInfluencerResource {

    private final Logger log = LoggerFactory.getLogger(InstagInfluencerResource.class);

    private static final String ENTITY_NAME = "instagInfluencer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstagInfluencerRepository instagInfluencerRepository;

    public InstagInfluencerResource(InstagInfluencerRepository instagInfluencerRepository) {
        this.instagInfluencerRepository = instagInfluencerRepository;
    }

    /**
     * {@code POST  /instag-influencers} : Create a new instagInfluencer.
     *
     * @param instagInfluencer the instagInfluencer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instagInfluencer, or with status {@code 400 (Bad Request)} if the instagInfluencer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/instag-influencers")
    public ResponseEntity<InstagInfluencer> createInstagInfluencer(@Valid @RequestBody InstagInfluencer instagInfluencer) throws URISyntaxException {
        log.debug("REST request to save InstagInfluencer : {}", instagInfluencer);
        if (instagInfluencer.getId() != null) {
            throw new BadRequestAlertException("A new instagInfluencer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstagInfluencer result = instagInfluencerRepository.save(instagInfluencer);
        return ResponseEntity.created(new URI("/api/instag-influencers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /instag-influencers} : Updates an existing instagInfluencer.
     *
     * @param instagInfluencer the instagInfluencer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instagInfluencer,
     * or with status {@code 400 (Bad Request)} if the instagInfluencer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instagInfluencer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/instag-influencers")
    public ResponseEntity<InstagInfluencer> updateInstagInfluencer(@Valid @RequestBody InstagInfluencer instagInfluencer) throws URISyntaxException {
        log.debug("REST request to update InstagInfluencer : {}", instagInfluencer);
        if (instagInfluencer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstagInfluencer result = instagInfluencerRepository.save(instagInfluencer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instagInfluencer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /instag-influencers} : get all the instagInfluencers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instagInfluencers in body.
     */
    @GetMapping("/instag-influencers")
    public List<InstagInfluencer> getAllInstagInfluencers() {
        log.debug("REST request to get all InstagInfluencers");
        return instagInfluencerRepository.findAll();
    }

    /**
     * {@code GET  /instag-influencers/:id} : get the "id" instagInfluencer.
     *
     * @param id the id of the instagInfluencer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instagInfluencer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/instag-influencers/{id}")
    public ResponseEntity<InstagInfluencer> getInstagInfluencer(@PathVariable Long id) {
        log.debug("REST request to get InstagInfluencer : {}", id);
        Optional<InstagInfluencer> instagInfluencer = instagInfluencerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(instagInfluencer);
    }

    /**
     * {@code DELETE  /instag-influencers/:id} : delete the "id" instagInfluencer.
     *
     * @param id the id of the instagInfluencer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/instag-influencers/{id}")
    public ResponseEntity<Void> deleteInstagInfluencer(@PathVariable Long id) {
        log.debug("REST request to delete InstagInfluencer : {}", id);
        instagInfluencerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
