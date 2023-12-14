package ru.mart.Hero.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mart.Hero.config.KafkaProducerConfig;
import ru.mart.Hero.dto.HeroDTO;
import ru.mart.Hero.dto.producer.AdapterResponse;
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    @Value("${spring.kafka.producer.topic.send.hero}")
    private String createHero;

    @Autowired
    private final KafkaProducerConfig producerConfig;

    public void sendCreateHeroSuccess(final HeroDTO message){
        sendMessage(createHero,message);
        log.info("Запись в топик произведена");
    }
    public void sendCreateHeroError(final AdapterResponse<HeroDTO> message){
        sendMessage(createHero,message);
    }
    //4.10.1 что такое Generics и зачем они появились в Java? Рассказать про стирание типов в java
    public <T> void sendMessage(final String topic,final T message){
        producerConfig.<T>getGenericProducerTemplate().send(topic,message);
    }
}
