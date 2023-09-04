package ru.mart.Hero.response;

import lombok.Getter;
import org.json.JSONObject;
import ru.mart.Hero.domain.Hero;
@Getter
public class GoodResponse {
    JSONObject json;
    public GoodResponse(Hero hero){
        json = new JSONObject().put("response",new JSONObject()
                .put("status","1")
                .put("data",new JSONObject()
                        .put("Hero",new JSONObject().put("id",hero.getId())
                                .put("name",hero.getName())
                                .put("level",hero.getLevel())
                                .put("ultimate",hero.getUltimate()))));
    }
}
