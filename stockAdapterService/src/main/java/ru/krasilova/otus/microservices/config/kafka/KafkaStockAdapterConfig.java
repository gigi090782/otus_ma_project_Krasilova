package ru.krasilova.otus.microservices.config.kafka;


import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaStockAdapterConfig {

    @Value("${kafka.stockAdapter.group.id}")
    protected String kafkaStockGroupId;

    @Value("${kafka.profile.group.id}")
    protected String kafkaProfileGroupId;

    @Value("${kafka.server}")
    protected String kafkaServer;


    @Bean
    public ProducerFactory<Object, String> producerStockAdapterEventFactory() {
        Map<String, Object> producerConfigs = getStockAdapterEventConfig(kafkaStockGroupId);
        return new DefaultKafkaProducerFactory<>(producerConfigs);
    }

    @Bean
    public KafkaTemplate<Object, String> kafkaStockAdapterEventTemplate(ProducerFactory<Object, String> producerStockAdapterFactory) {
        KafkaTemplate<Object, String> template = new KafkaTemplate<>(producerStockAdapterFactory);
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ConsumerFactory<String, String> consumerProfileFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProfileGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> singleProfileFactory(@Qualifier("consumerProfileFactory")ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(false);
        return factory;
    }



    private Map<String, Object> getStockAdapterEventConfig(final String clientId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(KafkaJsonSchemaSerializerConfig.ONEOF_FOR_NULLABLES, false);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        return props;

    }
}
