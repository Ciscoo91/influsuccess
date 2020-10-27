package com.ffu.web.rest;

import com.ffu.service.DiscussionService;
import com.ffu.service.dto.ChatDTO;
import com.ffu.service.dto.DiscussionDTO;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ffu.service.dto.DiscussionDTO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DiscussionResource {

    private final Logger log = LoggerFactory.getLogger(DiscussionResource.class);

    private static final String ENTITY_NAME = "discussion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiscussionService discussionService;

    public DiscussionResource(DiscussionService discussionService) {
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
    public ResponseEntity<DiscussionDTO> createDiscussion(@RequestBody DiscussionDTO discussion) throws URISyntaxException {
        log.debug("REST request to save DiscussionDTO : {}", discussion);
        if (discussion.getId() != null) {
            throw new BadRequestAlertException("A new discussion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiscussionDTO result = discussionService.save(discussion);
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
    public ResponseEntity<DiscussionDTO> updateDiscussion(@RequestBody DiscussionDTO discussion) throws URISyntaxException {
        log.debug("REST request to update DiscussionDTO : {}", discussion);
        if (discussion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiscussionDTO result = discussionService.save(discussion);
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
    public List<DiscussionDTO> getAllDiscussions() {
        log.debug("REST request to get all Discussions");
        return discussionService.findAll();
    }

    /**
     * {@code GET  /discussions/:id} : get the "id" discussion.
     *
     * @param id the id of the discussion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the discussion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/discussions/{id}")
    public ResponseEntity<DiscussionDTO> getDiscussion(@PathVariable Long id) {
        log.debug("REST request to get DiscussionDTO : {}", id);
        Optional<DiscussionDTO> discussion = discussionService.findById(id);
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
        log.debug("REST request to delete DiscussionDTO : {}", id);
        discussionService.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /discussions/:id/chat} : get the chat of the  discussion.
     * @param id the id of the discussion to retrieve.
     * @param userId
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the discussion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/discussions/{id}/chat")
    public ResponseEntity<ChatDTO> getChatDiscussion(@PathVariable Long id, @RequestParam(required = true) Long userId) {
        log.debug("REST request to get chat discussion : {}{}", id, userId);
        ChatDTO chat = discussionService.getChatDiscussion(id, userId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    /**
     * {@code GET  /discussions/existByParticipantAndCampaign} : get the chat of the  discussion if it exists.
     * @param userId
     * @param campaignId
     * @return
     */
    @GetMapping("discussions/existByParticipantAndCampaign")
    public ResponseEntity<ChatDTO> isAlreadyExistByParticipantAndCampaign(@RequestParam(required = true) Long userId, @RequestParam(required = true) Long campaignId){
        log.debug("REST request to get chat discussion by participant and campaign : {}{}", campaignId, userId);
        ChatDTO chat = discussionService.isAlreadyExistByParticipantAndCampaign(userId, campaignId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
