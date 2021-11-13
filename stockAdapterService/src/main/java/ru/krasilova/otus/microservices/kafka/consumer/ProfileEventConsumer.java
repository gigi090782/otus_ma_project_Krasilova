package ru.krasilova.otus.microservices.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import ru.krasilova.otus.microservices.kafka.consumer.handler.ProfileEventHandler;
import ru.krasilova.otus.microservices.kafka.dto.ProfileEventDto;

import static ru.krasilova.otus.microservices.metric.BrokerageMetric.PROFILE_EVENT_CONSUMED;


@Component
@Slf4j
public class ProfileEventConsumer {

    private final ProfileEventHandler profileEventHandler;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper objectMapper;

    public ProfileEventConsumer(ProfileEventHandler profileEventHandler,
                                MeterRegistry meterRegistry,
                                ObjectMapper objectMapper) {
        this.profileEventHandler = profileEventHandler;
        this.meterRegistry = meterRegistry;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            id = "profile-listener",
            groupId = "${kafka.profile.group.id}",
            topics = "#{'${kafka.profile.topics}'.split(',')}",
            containerFactory = "singleProfileFactory"
    )
    public void consume(@Payload GenericMessage<String> eventString, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        log.info("==>consumed  {} , topic  {}", eventString, topic);
        final var json = StringUtils.newStringUtf8(eventString.getPayload().getBytes());
        final var profileEventDto = objectMapper.readValue(json, ProfileEventDto.class);
        meterRegistry.counter(PROFILE_EVENT_CONSUMED.getMetricName()).increment();
        profileEventHandler.handle(profileEventDto);
    }
}
