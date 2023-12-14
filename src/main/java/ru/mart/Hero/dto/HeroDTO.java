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
    //4.11.5 Какие отличия между аyнотациями @NotEmpty, @NotBlank и @NotNull? К каким типам данных они применяются?
    @JsonProperty("ID")
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @Positive
    private Integer level;
    @NotNull
    private String ultimate;
}
