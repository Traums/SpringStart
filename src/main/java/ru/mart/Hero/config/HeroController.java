package ru.mart.Hero.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import ru.mart.Hero.response.Response;
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
            return new Response(true,errorMap,hero).getJson().toString();
        }else{
            hero.setId(100);
            JSONObject json = new Response(false,errorMap,hero).getJson();
            return json.toString();
        }
    }
    @PostMapping("/add")
    String addHero(@RequestBody Hero hero){
        service.saveHero(hero);
        return "Герой добавлен в БД";
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
