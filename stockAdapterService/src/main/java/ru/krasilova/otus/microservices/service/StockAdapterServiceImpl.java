package ru.krasilova.otus.microservices.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.krasilova.otus.microservices.database.entity.Message;
import ru.krasilova.otus.microservices.database.repository.MessageRepository;
import ru.krasilova.otus.microservices.exception.InvalidDataException;

@Service
@Slf4j
public class StockAdapterServiceImpl implements StockAdapterService {

    private final MessageRepository messageRepository;

    public StockAdapterServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(Message message) {
        try {
            return messageRepository.save(message);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException();
        }
    }


}
