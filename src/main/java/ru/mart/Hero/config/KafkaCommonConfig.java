package ru.mart.Hero.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Data
@RequiredArgsConstructor
public class KafkaCommonConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${spring.kafka.group-id}")
    private String groupId;

    @Value("${adapter.kafka.requestTimeout}")
    private String requestTimeout;

    @Value("${adapter.kafka.maxPollInterval}")
    private String maxPollInterval;

    @Value("${adapter.kafka.group.unique.prefix}")
    private String uniqueGroupPrefix;

    private final ObjectMapper mapper;
    @Bean
    public Map<String,Object> consumerConfig(){
        final Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        config.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,requestTimeout);
        config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,maxPollInterval);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return config;
    }
    @Bean
    public Map<String,Object> producerConfig(){
        final Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return config;
    }
}
