package ru.mart.Hero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

@Data
@Component
public class HeroDTO {
    @JsonProperty("ID")
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private int level;
    private String ultimate;
}
