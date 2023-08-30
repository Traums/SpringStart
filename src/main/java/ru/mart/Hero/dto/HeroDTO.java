package ru.mart.Hero.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HeroDTO {
    private long id;
    private String name;
    private int level;
    private String ultimate;
}
