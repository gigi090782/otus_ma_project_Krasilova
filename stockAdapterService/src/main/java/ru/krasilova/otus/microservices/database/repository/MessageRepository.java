package ru.krasilova.otus.microservices.database.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.krasilova.otus.microservices.database.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findById(Long ucpId);

    List<Message> findByProfileId(Long ucpId);

}
