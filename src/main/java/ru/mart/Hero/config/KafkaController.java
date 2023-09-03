package ru.mart.Hero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String sendKafkaReq(String msgId, String msg){
        kafkaTemplate.send("create_hero_req", msgId, msg);
        return "Запрос записан";
    }
}
