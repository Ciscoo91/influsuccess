package com.ffu.web.rest;

import com.ffu.domain.Influencer;
import com.ffu.repository.InfluencerRepository;
import com.ffu.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Influencer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfluencerResource {

    private final Logger log = LoggerFactory.getLogger(InfluencerResource.class);

    private static final String ENTITY_NAME = "influencer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfluencerRepository influencerRepository;

    public InfluencerResource(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    /**
     * {@code POST  /influencer-infos} : Create a new influencer.
     *
     * @param influencer the influencer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new influencer, or with status {@code 400 (Bad Request)} if the influencer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/influencer-infos")
    public ResponseEntity<Influencer> createInfluencer(@RequestBody Influencer influencer) throws URISyntaxException {
        log.debug("REST request to save Influencer : {}", influencer);
        if (influencer.getId() != null) {
            throw new BadRequestAlertException("A new influencer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Influencer result = influencerRepository.save(influencer);
        return ResponseEntity.created(new URI("/api/influencer-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /influencer-infos} : Updates an existing influencer.
     *
     * @param influencer the influencer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated influencer,
     * or with status {@code 400 (Bad Request)} if the influencer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the influencer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/influencer-infos")
    public ResponseEntity<Influencer> updateInfluencer(@RequestBody Influencer influencer) throws URISyntaxException {
        log.debug("REST request to update influencer : {}", influencer);
        if (influencer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Influencer result = influencerRepository.save(influencer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, influencer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /influencer-infos} : get all the influencer.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of influencer in body.
     */
    @GetMapping("/influencer-infos")
    public List<Influencer> getAllInfluencers() {
        log.debug("REST request to get all influencer");
        return influencerRepository.findAll();
    }

    /**
     * {@code GET  /influencer-infos/:id} : get the "id" influencer.
     *
     * @param id the id of the influencer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the influencer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/influencer-infos/{id}")
    public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
        log.debug("REST request to get influencer : {}", id);
        Optional<Influencer> influencer = influencerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(influencer);
    }

    /**
     * {@code DELETE  /influencer-infos/:id} : delete the "id" influencer.
     *
     * @param id the id of the influencerInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/influencer-infos/{id}")
    public ResponseEntity<Void> deleteInfluencerInfo(@PathVariable Long id) {
        log.debug("REST request to delete InfluencerInfo : {}", id);
        influencerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
