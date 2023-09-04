package ru.mart.Hero.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
//@SpringBootApplication
public class KafkaProducer {
    @KafkaListener(topics="create_hero_req")
    public void msgListener(String msg){
        System.out.println(msg);
    }
}
