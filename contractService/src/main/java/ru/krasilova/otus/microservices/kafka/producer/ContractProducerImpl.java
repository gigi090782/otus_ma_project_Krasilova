package ru.krasilova.otus.microservices.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.krasilova.otus.microservices.kafka.dto.ContractEventDto;

import static ru.krasilova.otus.microservices.metric.BrokerageMetric.CONTRACT_PRODUCED;

@Component
@Slf4j
public class ContractProducerImpl implements ContractProducer {

    private final KafkaTemplate<Object, String> kafkaTemplate;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper mapper;

    public ContractProducerImpl(@Qualifier("kafkaContractEventTemplate") KafkaTemplate<Object, String> kafkaTemplate,
                                MeterRegistry meterRegistry, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.meterRegistry = meterRegistry;
        this.mapper = mapper;
    }

    @Override
    public void sendContactEvent(@NotNull ContractEventDto contractEventDto, @NotNull ContractTopic contractTopic) throws JsonProcessingException {
        log.info("<=produced {}", contractEventDto);
        kafkaTemplate.send(contractTopic.getValue(), mapper.writeValueAsString(contractEventDto));
        meterRegistry.counter(CONTRACT_PRODUCED.getMetricName()).increment();
    }

}
