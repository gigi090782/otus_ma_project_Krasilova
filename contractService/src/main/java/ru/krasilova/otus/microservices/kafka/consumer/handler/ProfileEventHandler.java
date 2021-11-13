package ru.krasilova.otus.microservices.kafka.consumer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.krasilova.otus.microservices.kafka.dto.ProfileEventDto;

public interface ProfileEventHandler {
    void handle(ProfileEventDto profileEventDto, String topic) throws JsonProcessingException;
}
