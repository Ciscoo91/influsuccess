package com.ffu.web.rest;

import com.ffu.service.MessageService;
import com.ffu.service.dto.MessageChat;
import com.ffu.service.dto.MessageDTO;
import com.ffu.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.ffu.domain.Message}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MessageResource {
    private final Logger log = LoggerFactory.getLogger(MessageResource.class);

    private static final String ENTITY_NAME = "message";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * {@code POST  /messages} : Create a new message.
     *
     * @param message the message to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new message, or with status {@code 400 (Bad Request)} if the message has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/messages")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO message) throws URISyntaxException {
        log.debug("REST request to save Message : {}", message);
        if (message.getId() != null) {
            throw new BadRequestAlertException("A new message cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageDTO result = messageService.save(message);
        return ResponseEntity
            .created(new URI("/api/messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /messages} : Updates an existing message.
     *
     * @param message the message to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated message,
     * or with status {@code 400 (Bad Request)} if the message is not valid,
     * or with status {@code 500 (Internal Server Error)} if the message couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/messages")
    public ResponseEntity<MessageDTO> updateMessage(@Valid @RequestBody MessageDTO message) throws URISyntaxException {
        log.debug("REST request to update Message : {}", message);
        if (message.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessageDTO result = messageService.save(message);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, message.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /messages} : get all the messages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messages in body.
     */
    @GetMapping("/messages")
    public List<MessageDTO> getAllMessages() {
        log.debug("REST request to get all Messages");
        return messageService.findAll();
    }

    /**
     * {@code GET  /messages/:id} : get the "id" message.
     *
     * @param id the id of the message to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the message, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long id) {
        log.debug("REST request to get Message : {}", id);
        Optional<MessageDTO> message = messageService.findById(id);
        return ResponseUtil.wrapOrNotFound(message);
    }

    /**
     * {@code DELETE  /messages/:id} : delete the "id" message.
     *
     * @param id the id of the message to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.debug("REST request to delete Message : {}", id);
        messageService.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/messages/countNew")
    public ResponseEntity<Long> getCountNewMessages(
        @RequestParam(required = true) Long userId,
        @RequestParam(required = true) Long discussionId
    ) {
        log.debug("REST request to getCountNewMessages : {}{}", userId, discussionId);
        Long count = messageService.getCountNewMessages(userId, discussionId);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/messages/alllNewMessageCount")
    public ResponseEntity<Long> getAllNewMessageCount(@RequestParam(required = true) Long userId) {
        log.debug("REST request to alllNewMessageCount :");
        Long count = messageService.getAlllNewMessageCount(userId);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * {@code POST  /messages} : Create a new message.
     *
     * @param messageChat the message of the chat to save.
     * @parm discussionId the id of the discussion
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageChat, or with status {@code 400 (Bad Request)} if the message has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/messages/messageChat")
    public ResponseEntity<MessageChat> saveMessageChat(
        @Valid @RequestBody MessageChat messageChat,
        @RequestParam(required = true) Long discussionId
    )
        throws URISyntaxException {
        log.debug("REST request to save MessageChat : {}", messageChat);
        MessageChat result = messageService.saveMessageChat(messageChat, discussionId);
        return ResponseEntity.created(new URI("/api/messages/messageChat" + result)).body(result);
    }
}
