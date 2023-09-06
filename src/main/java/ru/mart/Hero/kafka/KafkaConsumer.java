package ru.mart.Hero.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.mart.Hero.dto.HeroDTO;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "${spring.kafka.consumer.topic.get.hero}",
                   groupId = "${spring.kafka.group-id}",
                   containerFactory = "getCreateHeroContainerFactory")
    public void consume(HeroDTO record){
        System.out.println(record.getName());
    }
}
