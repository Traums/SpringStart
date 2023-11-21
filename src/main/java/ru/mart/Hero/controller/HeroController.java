package ru.mart.Hero.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import ru.mart.Hero.dto.HeroDTO;
import ru.mart.Hero.response.BadResponse;
import ru.mart.Hero.response.GoodResponse;
import ru.mart.Hero.service.DatabaseService;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.util.Validation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hero")
public class HeroController {
    List<ObjectError> errorMap = new ArrayList<>();
    private final DatabaseService service;
    @Autowired
    Validation validation;
    HeroController(DatabaseService service){
        this.service = service;
    }
    @GetMapping()
    String getHero(){
        return new JSONObject().put("hero",new JSONObject()
                                            .put("id",1)
                                            .put("name","Pudge")
                                            .put("lvl",1))
                                            .toString();
    }
    @PostMapping()
    String modifyHero(@Valid @RequestBody Hero hero){
        if (validate(hero)){
            hero.setId(100);
            return new GoodResponse(hero).getJson().toString();
        }else{
            return new BadResponse(errorMap).getJson().toString();
        }
    }
    @PostMapping("/add")
    String addHero(@Validated @RequestBody Hero hero){
        if (validate(hero)){
            service.saveHero(hero);
            return new GoodResponse(hero).getJson().toString();
        }else{
            return new BadResponse(errorMap).getJson().toString();
        }
//        return "Тестовый прогон";
    }
    @GetMapping("/list")
    List<Hero> getAll(){
        return service.getAll();
    }
    @RequestMapping("/{id}")
    Hero getHeroById(@PathVariable long id){
        return service.getHeroById(id);
    }
    private boolean validate(Hero hero){
        boolean result = false;

        final DataBinder dataBinder = new DataBinder(hero);
        dataBinder.addValidators(validation);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors()) {
            errorMap.addAll(dataBinder.getBindingResult().getAllErrors());
        }
        if (errorMap.isEmpty())
            result = true;
        return result;
    }
}
