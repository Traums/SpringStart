package ru.mart.Hero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Component
public class HeroDTO {
    @JsonProperty("ID")
    @NotNull(message = "обязательное поле")
    private long id;
    @NotBlank(message = "Поле должно быть заполнено")
    private String name;
    @Positive
    private Integer level;
    @NotNull
    private String ultimate;
}
