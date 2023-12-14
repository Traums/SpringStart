package ru.mart.Practice.Serialization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Pudge extends Hero implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public Pudge(int id, String name , int level, String ultimate) {
        super(id,name,level,ultimate);
    }
}
