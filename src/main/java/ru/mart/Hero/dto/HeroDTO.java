package ru.mart.Hero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Component
public class HeroDTO {
    @JsonProperty("ID")
    @NotNull(message = "обязательное поле")
    private long id;
    @NotBlank(message = "Поле должно быть заполнено")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    private Integer level;
    private String ultimate;
}
