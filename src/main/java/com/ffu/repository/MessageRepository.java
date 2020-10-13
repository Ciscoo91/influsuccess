package com.ffu.repository;

import com.ffu.domain.Message;
import com.ffu.repository.custom.MessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {

    @Query("select message from Message message where message.sender.login = ?#{principal.username}")
    List<Message> findBySenderIsCurrentUser();

    @Query("select message from Message message where message.receiver.login = ?#{principal.username}")
    List<Message> findByReceiverIsCurrentUser();

}
