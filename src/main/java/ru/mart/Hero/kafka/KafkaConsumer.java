package ru.mart.Hero.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mart.Hero.dto.HeroDTO;
import ru.mart.Hero.mapping.MapperHero;
import ru.mart.Hero.DatabaseService.DatabaseService;
@Slf4j
@Component
public class KafkaConsumer {

    private final KafkaProducer producer;
    private final DatabaseService service;
    private final MapperHero mapperHero;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public KafkaConsumer(KafkaProducer producer, DatabaseService service, MapperHero mapperHero) {
        this.producer = producer;
        this.service = service;
        this.mapperHero = mapperHero;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic.get.hero}",
                   groupId = "${spring.kafka.group-id}",
                   containerFactory = "getCreateHeroContainerFactory")
    public void consume(HeroDTO heroDto){
        log.info("Сообщение получено и обработано");
        heroDto.setId(heroDto.getId() + 2);//Изменённый id
        service.saveHero(mapperHero.mapToHeroEntity(heroDto));
        producer.sendCreateHeroSuccess(heroDto);
    }
}










