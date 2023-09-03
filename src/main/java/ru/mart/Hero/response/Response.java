package ru.mart.Hero.response;

import lombok.Data;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.validation.ObjectError;
import ru.mart.Hero.domain.Hero;
import java.util.List;

@Data
public class Response {
    JSONObject json;
    public Response(boolean success, List<ObjectError> errorMap,Hero hero){
        if (success){
            json = new JSONObject().put("response",new JSONObject()
                                                        .put("status","1")
                                                        .put("data",new JSONObject()
                                                                            .put("Hero",new JSONObject().put("id",hero.getId())
                                                                                                        .put("name",hero.getName())
                                                                                                        .put("level",hero.getLevel())
                                                                                                        .put("ultimate",hero.getUltimate()))));
        }else{
            JSONObject errorData = new JSONObject();
            for( ObjectError error : errorMap){
                errorData.put(error.getCode(),error.getDefaultMessage());
            }
            json = new JSONObject().put("response",new JSONObject()
                                                        .put("status","0")
                                                        .put("data",errorData));
        }
    }
}
