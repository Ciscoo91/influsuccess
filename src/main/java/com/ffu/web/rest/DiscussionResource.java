package com.ffu.web.rest;

import com.ffu.domain.Discussion;
import com.ffu.repository.DiscussionRepository;
import com.ffu.service.DiscussionService;
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
 * REST controller for managing {@link com.ffu.domain.Discussion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DiscussionResource {

    private final Logger log = LoggerFactory.getLogger(DiscussionResource.class);

    private static final String ENTITY_NAME = "discussion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiscussionRepository discussionRepository;

    private final DiscussionService discussionService;

    public DiscussionResource(DiscussionRepository discussionRepository, DiscussionService discussionService) {
        this.discussionRepository = discussionRepository;
        this.discussionService = discussionService;
    }

    /**
     * {@code POST  /discussions} : Create a new discussion.
     *
     * @param discussion the discussion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new discussion, or with status {@code 400 (Bad Request)} if the discussion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/discussions")
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion) throws URISyntaxException {
        log.debug("REST request to save Discussion : {}", discussion);
        if (discussion.getId() != null) {
            throw new BadRequestAlertException("A new discussion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Discussion result = discussionRepository.save(discussion);
        return ResponseEntity.created(new URI("/api/discussions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /discussions} : Updates an existing discussion.
     *
     * @param discussion the discussion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated discussion,
     * or with status {@code 400 (Bad Request)} if the discussion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the discussion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/discussions")
    public ResponseEntity<Discussion> updateDiscussion(@RequestBody Discussion discussion) throws URISyntaxException {
        log.debug("REST request to update Discussion : {}", discussion);
        if (discussion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Discussion result = discussionRepository.save(discussion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, discussion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /discussions} : get all the discussions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of discussions in body.
     */
    @GetMapping("/discussions")
    public List<Discussion> getAllDiscussions() {
        log.debug("REST request to get all Discussions");
        return discussionRepository.findAll();
    }

    /**
     * {@code GET  /discussions/:id} : get the "id" discussion.
     *
     * @param id the id of the discussion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the discussion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/discussions/{id}")
    public ResponseEntity<Discussion> getDiscussion(@PathVariable Long id) {
        log.debug("REST request to get Discussion : {}", id);
        Optional<Discussion> discussion = discussionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(discussion);
    }

    /**
     * {@code DELETE  /discussions/:id} : delete the "id" discussion.
     *
     * @param id the id of the discussion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/discussions/{id}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        log.debug("REST request to delete Discussion : {}", id);
        discussionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
