package ru.mart.Hero.response;

import lombok.Getter;
import org.json.JSONObject;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadResponse {
    JSONObject json;
    public BadResponse(List<ObjectError> errorMap){
        JSONObject errorData = new JSONObject();
        for( ObjectError error : errorMap){
            errorData.put(error.getCode(),error.getDefaultMessage());
        }
        json = new JSONObject().put("response",new JSONObject()
                .put("status","0")
                .put("data",errorData));
    }
}
