package com.ffu.web.rest;

import com.ffu.repository.dto.InfluencerSearchDTO;
import com.ffu.service.InfluencerService;
import com.ffu.service.dto.InfluencerDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing {@link com.ffu.domain.Influencer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InfluencerResource {

    private final Logger log = LoggerFactory.getLogger(InfluencerResource.class);

    private static final String ENTITY_NAME = "influencerDTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfluencerService influencerService;

    public InfluencerResource(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    /**
     * {@code GET  /influencers} : get all the influencers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of influencers in body.
     */
    @GetMapping("/influencers")
    public ResponseEntity<List<InfluencerDTO>> getAllInfluencers() {
        log.debug("REST request to get all Influencers");
        List<InfluencerDTO> result = influencerService.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /influencers/:id} : get the "id" influencerDTO.
     *
     * @param id the id of the influencerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the influencerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/influencers/{id}")
    public ResponseEntity<InfluencerDTO> getInfluencer(@PathVariable Long id) {
        log.debug("REST request to get InfluencerDTO : {}", id);
        Optional<InfluencerDTO> influencerDTO = influencerService.findById(id);
        return ResponseUtil.wrapOrNotFound(influencerDTO);
    }

    /**
     * {@code DELETE  /influencers/:id} : delete the "id" influencerDTO.
     *
     * @param id the id of the influencerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/influencers/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
        log.debug("REST request to delete InfluencerDTO : {}", id);
        influencerService.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/influencers/page")
    public ResponseEntity<Page<InfluencerDTO>> getInfluencerPageable(@RequestBody InfluencerSearchDTO influencerSearchDTO, Pageable pageable){
        log.debug("Rest request to get filtered and paginated influencers");
        Page<InfluencerDTO> result = (Page<InfluencerDTO>) influencerService.getInfluencerSearchPageable(influencerSearchDTO, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
