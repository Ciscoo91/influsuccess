package com.ffu.web.rest;

import com.ffu.domain.SocialNetwork;
import com.ffu.repository.SocialNetworkRepository;
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
 * REST controller for managing {@link com.ffu.domain.SocialNetwork}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SocialNetworkResource {

    private final Logger log = LoggerFactory.getLogger(SocialNetworkResource.class);

    private static final String ENTITY_NAME = "socialNetwork";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocialNetworkRepository socialNetworkRepository;

    public SocialNetworkResource(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    /**
     * {@code POST  /social-networks} : Create a new socialNetwork.
     *
     * @param socialNetwork the socialNetwork to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new socialNetwork, or with status {@code 400 (Bad Request)} if the socialNetwork has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/social-networks")
    public ResponseEntity<SocialNetwork> createSocialNetwork(@Valid @RequestBody SocialNetwork socialNetwork) throws URISyntaxException {
        log.debug("REST request to save SocialNetwork : {}", socialNetwork);
        if (socialNetworkRepository.findById(socialNetwork.getName()).isPresent() ) {
            throw new BadRequestAlertException("already name exist ", ENTITY_NAME, "idexist");
        }
        SocialNetwork result = socialNetworkRepository.save(socialNetwork);
        return ResponseEntity.created(new URI("/api/social-networks/" + result.getName()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /social-networks} : get all the socialNetworks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of socialNetworks in body.
     */
    @GetMapping("/social-networks")
    public List<SocialNetwork> getAllSocialNetworks() {
        log.debug("REST request to get all SocialNetworks");
        return socialNetworkRepository.findAll();
    }

    /**
     * {@code GET  /social-networks/:id} : get the "id" socialNetwork.
     *
     * @param id the id of the socialNetwork to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the socialNetwork, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/social-networks/{id}")
    public ResponseEntity<SocialNetwork> getSocialNetwork(@PathVariable String id) {
        log.debug("REST request to get SocialNetwork : {}", id);
        Optional<SocialNetwork> socialNetwork = socialNetworkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(socialNetwork);
    }

    /**
     * {@code DELETE  /social-networks/:id} : delete the "id" socialNetwork.
     *
     * @param id the id of the socialNetwork to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/social-networks/{id}")
    public ResponseEntity<Void> deleteSocialNetwork(@PathVariable String id) {
        log.debug("REST request to delete SocialNetwork : {}", id);
        socialNetworkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
