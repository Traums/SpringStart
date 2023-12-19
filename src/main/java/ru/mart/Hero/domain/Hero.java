package ru.mart.Hero.domain;

import jakarta.persistence.Version;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "heroes")
public class Hero {
    @Version
    private long versional;
    @Id
    private long id;
    @NotBlank(message = "Имя должно быть заполнено")
    private String name;
    private int level;
    private String ultimate;
}
