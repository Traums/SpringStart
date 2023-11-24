package ru.mart.Hero.mapping;

import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.dto.HeroDTO;
import org.springframework.stereotype.Service;

@Service
public class MapperHero {
    public static HeroDTO mapToHeroDTO(Hero entity){
        HeroDTO dto = new HeroDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setUltimate(entity.getUltimate());
        return dto;
    }
    public static Hero mapToHeroEntity(HeroDTO dto){
        Hero entity = new Hero();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setUltimate(dto.getUltimate());
        return entity;
    }
}
