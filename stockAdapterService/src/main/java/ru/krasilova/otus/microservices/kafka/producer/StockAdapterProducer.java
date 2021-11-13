package ru.krasilova.otus.microservices.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.krasilova.otus.microservices.kafka.dto.StockAdapterEventDto;

public interface StockAdapterProducer {

    void sendStockEventEvent(StockAdapterEventDto stockAdapterEventDto) throws JsonProcessingException;
}
