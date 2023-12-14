package ru.mart.Practice.Serialization;


import java.util.List;

public class SerializeJob {
    public Serializer serial;
    SerializeJob(String filename){
        this.serial = new Serializer(filename);
    }
    void serializePudge(Pudge pud){
        this.serial.serialize(pud);
    }
    List<Pudge> deserialize(){
        return this.serial.deserialize();
    }
    void output(List<Pudge> pudgeCol){
        for (Pudge pudge : pudgeCol) {
            System.out.println(pudge.getId() + " " +
                    pudge.getName() + " " +
                    pudge.getLevel() + " " +
                    pudge.getUltimate());
        }
    }
}
