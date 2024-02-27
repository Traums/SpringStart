package ru.mart.Hero.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.LockModeType;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mart.Hero.DatabaseService.DatabaseService;
import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.dto.HeroDTO;
import ru.mart.Hero.mapping.MapperHero;
import ru.mart.Hero.repos.HeroRepository;
import ru.mart.Hero.response.BadResponse;
import ru.mart.Hero.response.GoodResponse;
import ru.mart.Hero.util.Validation;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/hero")
@Validated
@Api("Api для работы с сущностью Hero")
//4.11.10 Расскажите о использовании аннотаций и их параметрах @RequestMapping, @RequestBody, @PathVariable, @RequestParam,
public class HeroController {
    List<ObjectError> errorMap = new ArrayList<>();
    private final DatabaseService service;
    //2.5 D - Dependency Inversion Principle (Критический взгляд на DIP)
    @Autowired
    HeroRepository heroRepository;
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
    //4.11.7 Рассказать про использование аннотаций @Query @Procedure @Lock @EnableJpaRepositories
    @Lock(LockModeType.WRITE)
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
    @Lock(LockModeType.READ)
    @Operation(summary = "Получение записи из БД по id")
    Hero getHeroById(@PathVariable long id){
        return service.getHeroById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return new ResponseEntity<>("Возникла необработнная ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/find/{name}")
    Hero getIdByName(@PathVariable String name){
        return heroRepository.findIdByFirstname(name);
    }
    @GetMapping("/getTotal/{model}")
    int getTotalHeroes(@PathVariable String model){
        return heroRepository.getTotalHeroes(model);
    }
    @GetMapping("/param")
    public String getUserInfo(@RequestParam String id, @RequestParam String name) {
        return "User info: ID = " + id + ", Name = " + name;
    }
}
