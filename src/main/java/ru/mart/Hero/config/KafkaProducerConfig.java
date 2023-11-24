package ru.mart.Hero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
@Scope("singleton")
public class KafkaProducerConfig {
    @Autowired
    private KafkaCommonConfig commonConfig;

    @Bean
    public <T> ProducerFactory<String,T> getGenericProducerFactory(){
        return new DefaultKafkaProducerFactory<>(commonConfig.producerConfig());
    }
    @Bean
    public <T>KafkaTemplate<String,T> getGenericProducerTemplate(){
        return new KafkaTemplate<>(getGenericProducerFactory());
    }
}
