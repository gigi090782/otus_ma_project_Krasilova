package ru.krasilova.otus.microservices.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jetbrains.annotations.NotNull;
import ru.krasilova.otus.microservices.kafka.dto.ContractEventDto;

public interface ContractProducer {

    void sendContactEvent(ContractEventDto contractEventDto, @NotNull ContractTopic contractTopic) throws JsonProcessingException;
}
