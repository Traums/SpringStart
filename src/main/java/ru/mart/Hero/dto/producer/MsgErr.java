package ru.mart.Hero.dto.producer;

import lombok.Getter;

public class MsgErr {
    @Getter
    private String code;
    @Getter
    private String textEn;

    public MsgErr setCode(String code){
        this.code = code;
        return this;
    }
    public MsgErr setTextEn(String textEn){
        this.textEn = textEn;
        return this;
    }
    @Override
    public String toString(){
        return "MsgErr [code =" + code + ", textEn=" + textEn + "]";
    }
}
