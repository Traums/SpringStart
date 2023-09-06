package ru.mart.Hero.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.mart.Hero.dto.HeroDTO;

@EnableKafka
//@SpringBootApplication
public class KafkaProducer {
    @KafkaListener(topics="create_hero_resp",groupId = "app.1",containerFactory = "")
    public void msgListener(HeroDTO heroDTO){
        System.out.println(heroDTO.getId());
    }
}
