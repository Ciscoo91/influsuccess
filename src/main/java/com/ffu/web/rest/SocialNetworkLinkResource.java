package com.ffu.web.rest;

import com.ffu.domain.SocialNetworkLink;
import com.ffu.repository.SocialNetworkLinkRepository;
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
 * REST controller for managing {@link com.ffu.domain.SocialNetworkLink}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SocialNetworkLinkResource {

    private final Logger log = LoggerFactory.getLogger(SocialNetworkLinkResource.class);

    private static final String ENTITY_NAME = "socialNetworkLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocialNetworkLinkRepository socialNetworkLinkRepository;

    public SocialNetworkLinkResource(SocialNetworkLinkRepository socialNetworkLinkRepository) {
        this.socialNetworkLinkRepository = socialNetworkLinkRepository;
    }

    /**
     * {@code POST  /social-network-links} : Create a new socialNetworkLink.
     *
     * @param socialNetworkLink the socialNetworkLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new socialNetworkLink, or with status {@code 400 (Bad Request)} if the socialNetworkLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/social-network-links")
    public ResponseEntity<SocialNetworkLink> createSocialNetworkLink(@Valid @RequestBody SocialNetworkLink socialNetworkLink) throws URISyntaxException {
        log.debug("REST request to save SocialNetworkLink : {}", socialNetworkLink);
        if (socialNetworkLink.getId() != null) {
            throw new BadRequestAlertException("A new socialNetworkLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocialNetworkLink result = socialNetworkLinkRepository.save(socialNetworkLink);
        return ResponseEntity.created(new URI("/api/social-network-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /social-network-links} : Updates an existing socialNetworkLink.
     *
     * @param socialNetworkLink the socialNetworkLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialNetworkLink,
     * or with status {@code 400 (Bad Request)} if the socialNetworkLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the socialNetworkLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/social-network-links")
    public ResponseEntity<SocialNetworkLink> updateSocialNetworkLink(@Valid @RequestBody SocialNetworkLink socialNetworkLink) throws URISyntaxException {
        log.debug("REST request to update SocialNetworkLink : {}", socialNetworkLink);
        if (socialNetworkLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocialNetworkLink result = socialNetworkLinkRepository.save(socialNetworkLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialNetworkLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /social-network-links} : get all the socialNetworkLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of socialNetworkLinks in body.
     */
    @GetMapping("/social-network-links")
    public List<SocialNetworkLink> getAllSocialNetworkLinks() {
        log.debug("REST request to get all SocialNetworkLinks");
        return socialNetworkLinkRepository.findAll();
    }

    /**
     * {@code GET  /social-network-links/:id} : get the "id" socialNetworkLink.
     *
     * @param id the id of the socialNetworkLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the socialNetworkLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/social-network-links/{id}")
    public ResponseEntity<SocialNetworkLink> getSocialNetworkLink(@PathVariable Long id) {
        log.debug("REST request to get SocialNetworkLink : {}", id);
        Optional<SocialNetworkLink> socialNetworkLink = socialNetworkLinkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(socialNetworkLink);
    }

    /**
     * {@code DELETE  /social-network-links/:id} : delete the "id" socialNetworkLink.
     *
     * @param id the id of the socialNetworkLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/social-network-links/{id}")
    public ResponseEntity<Void> deleteSocialNetworkLink(@PathVariable Long id) {
        log.debug("REST request to delete SocialNetworkLink : {}", id);
        socialNetworkLinkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
