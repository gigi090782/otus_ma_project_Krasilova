package ru.krasilova.otus.microservices.kafka.consumer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.krasilova.otus.microservices.database.entity.Message;
import ru.krasilova.otus.microservices.kafka.dto.ProfileEventDto;
import ru.krasilova.otus.microservices.kafka.dto.StockAdapterEventDto;
import ru.krasilova.otus.microservices.kafka.producer.StockAdapterProducer;
import ru.krasilova.otus.microservices.model.StockAdapterAnswer;
import ru.krasilova.otus.microservices.service.StockAdapterService;

@Component
@Slf4j
public class ProfileEventHandlerImpl implements ProfileEventHandler {

    private final StockAdapterService stockAdapterService;
    private final StockAdapterProducer producer;
    private final ObjectMapper mapper;

    public ProfileEventHandlerImpl(StockAdapterService stockAdapterService, StockAdapterProducer producer, ObjectMapper mapper) {
        this.stockAdapterService = stockAdapterService;
        this.producer = producer;
        this.mapper = mapper;
    }


    @Override
    public void handle(ProfileEventDto profileEventDto) throws JsonProcessingException {
        log.info("Handle profile event {}", profileEventDto);
        final var message = Message.builder()
                .profileId(profileEventDto.getProfile().getId())
                .message(mapper.writeValueAsString(profileEventDto.toString()))
                .build();
        stockAdapterService.createMessage(message);
        final var answer = StockAdapterEventDto.builder()
                .profileId(profileEventDto.getProfile().getId())
                .profileStatus(StockAdapterAnswer.REGISTERED.getValue())
                .build();
        producer.sendStockEventEvent(answer);
    }
}
