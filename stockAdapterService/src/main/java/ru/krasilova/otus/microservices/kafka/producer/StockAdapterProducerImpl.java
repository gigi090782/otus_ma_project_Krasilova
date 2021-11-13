package ru.krasilova.otus.microservices.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.krasilova.otus.microservices.kafka.dto.StockAdapterEventDto;

import static ru.krasilova.otus.microservices.metric.BrokerageMetric.STOCK_ADAPTER_PRODUCED;

@Component
@Slf4j
public class StockAdapterProducerImpl implements StockAdapterProducer {

    private final KafkaTemplate<Object, String> kafkaTemplate;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper mapper;


    @Value("${kafka.stockAdapter.topic}")
    private String kafkaTopicId;

    public StockAdapterProducerImpl(@Qualifier("kafkaStockAdapterEventTemplate") KafkaTemplate<Object, String> kafkaTemplate,
                                    MeterRegistry meterRegistry, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.meterRegistry = meterRegistry;
        this.mapper = mapper;
    }

    @Override
    public void sendStockEventEvent(@NotNull StockAdapterEventDto stockAdapterEventDto) throws JsonProcessingException {
        log.info("<=produced {}", stockAdapterEventDto);
        kafkaTemplate.send(kafkaTopicId, mapper.writeValueAsString(stockAdapterEventDto));
        meterRegistry.counter(STOCK_ADAPTER_PRODUCED.getMetricName()).increment();
    }

}
