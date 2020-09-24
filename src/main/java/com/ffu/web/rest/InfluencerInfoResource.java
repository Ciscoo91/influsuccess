package com.ffu.web.rest;

import com.ffu.domain.InfluencerInfo;
import com.ffu.repository.InfluencerInfoRepository;
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
 * REST controller for managing {@link com.ffu.domain.InfluencerInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfluencerInfoResource {

    private final Logger log = LoggerFactory.getLogger(InfluencerInfoResource.class);

    private static final String ENTITY_NAME = "influencerInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfluencerInfoRepository influencerInfoRepository;

    public InfluencerInfoResource(InfluencerInfoRepository influencerInfoRepository) {
        this.influencerInfoRepository = influencerInfoRepository;
    }

    /**
     * {@code POST  /influencer-infos} : Create a new influencerInfo.
     *
     * @param influencerInfo the influencerInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new influencerInfo, or with status {@code 400 (Bad Request)} if the influencerInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/influencer-infos")
    public ResponseEntity<InfluencerInfo> createInfluencerInfo(@RequestBody InfluencerInfo influencerInfo) throws URISyntaxException {
        log.debug("REST request to save InfluencerInfo : {}", influencerInfo);
        if (influencerInfo.getId() != null) {
            throw new BadRequestAlertException("A new influencerInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfluencerInfo result = influencerInfoRepository.save(influencerInfo);
        return ResponseEntity.created(new URI("/api/influencer-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /influencer-infos} : Updates an existing influencerInfo.
     *
     * @param influencerInfo the influencerInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated influencerInfo,
     * or with status {@code 400 (Bad Request)} if the influencerInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the influencerInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/influencer-infos")
    public ResponseEntity<InfluencerInfo> updateInfluencerInfo(@RequestBody InfluencerInfo influencerInfo) throws URISyntaxException {
        log.debug("REST request to update InfluencerInfo : {}", influencerInfo);
        if (influencerInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfluencerInfo result = influencerInfoRepository.save(influencerInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, influencerInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /influencer-infos} : get all the influencerInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of influencerInfos in body.
     */
    @GetMapping("/influencer-infos")
    public List<InfluencerInfo> getAllInfluencerInfos() {
        log.debug("REST request to get all InfluencerInfos");
        return influencerInfoRepository.findAll();
    }

    /**
     * {@code GET  /influencer-infos/:id} : get the "id" influencerInfo.
     *
     * @param id the id of the influencerInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the influencerInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/influencer-infos/{id}")
    public ResponseEntity<InfluencerInfo> getInfluencerInfo(@PathVariable Long id) {
        log.debug("REST request to get InfluencerInfo : {}", id);
        Optional<InfluencerInfo> influencerInfo = influencerInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(influencerInfo);
    }

    /**
     * {@code DELETE  /influencer-infos/:id} : delete the "id" influencerInfo.
     *
     * @param id the id of the influencerInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/influencer-infos/{id}")
    public ResponseEntity<Void> deleteInfluencerInfo(@PathVariable Long id) {
        log.debug("REST request to delete InfluencerInfo : {}", id);
        influencerInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
