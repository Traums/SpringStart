package ru.mart.Hero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.mart.Hero.dto.HeroDTO;


@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<Object, HeroDTO> kafkaTemplate;
    @PostMapping
    public String sendKafkaReq(@RequestBody HeroDTO heroDTO){
        kafkaTemplate.send("create_hero_req", heroDTO);
        return "Запрос записан";
    }
}
