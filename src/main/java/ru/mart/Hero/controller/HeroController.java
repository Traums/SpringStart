package ru.mart.Hero.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import ru.mart.Hero.dto.HeroDTO;
import ru.mart.Hero.mapping.MapperHero;
import ru.mart.Hero.response.BadResponse;
import ru.mart.Hero.response.GoodResponse;
import ru.mart.Hero.DatabaseService.DatabaseService;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.util.Validation;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/hero")
@Validated
@Api("Api для работы с сущностью Hero")
public class HeroController {
    List<ObjectError> errorMap = new ArrayList<>();
    private final DatabaseService service;
    @Autowired
    Validation validation;
    HeroController(DatabaseService service){
        this.service = service;
    }
    @GetMapping()
    @Operation(summary = "Тестовый запрос")
    String getHero(){
        return new JSONObject().put("hero",new JSONObject()
                                            .put("id",1)
                                            .put("name","Pudge")
                                            .put("lvl",1))
                                            .toString();
    }
    @PostMapping()
    @Operation(summary = "Апдейт Hero")
    String modifyHero(@Valid @RequestBody Hero hero){
        if (validate(hero)){
            hero.setId(100);
            return new GoodResponse(hero).getJson().toString();
        }else{
            return new BadResponse(errorMap).getJson().toString();
        }
    }
    @PostMapping("/add")
    @Operation(summary = "Добавление сущности в БД")
    String addHero(@Valid @RequestBody HeroDTO heroDTO){
        Hero hero = MapperHero.mapToHeroEntity(heroDTO);
        if (validate(hero)){
            service.saveHero(hero);
            return new GoodResponse(hero).getJson().toString();
        }else{
            return new BadResponse(errorMap).getJson().toString();
        }
    }
    @GetMapping("/list")
    @Operation(summary = "Получение всех записей из БД")
    List<Hero> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение записи из БД по id")
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
