package ru.mart.Practice.Serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero{
    protected long id;
    protected String Name;
    protected int level;
    protected String Ultimate;
}
