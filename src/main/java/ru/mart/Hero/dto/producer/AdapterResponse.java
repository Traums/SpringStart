package ru.mart.Hero.dto.producer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdapterResponse<T> {
    @JsonProperty("ID")
    private String id;

    private Integer status = 0;

    private MsgErr msgErr;

    private T result;
    @Override
    public String toString(){
        return "AdapterResponse [ID=" + id + ", status=" + status + ", msgErr=" + msgErr + ", result=" + result + "]";
    }
}
