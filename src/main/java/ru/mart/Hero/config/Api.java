package ru.mart.Hero.config;


import ru.mart.Hero.service.DatabaseService;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import ru.mart.Hero.domain.Hero;

@RestController
@RequestMapping("/api/hero")
public class Api {
//    @Autowired
    private final DatabaseService service;

    Api(DatabaseService service){
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
    Hero modifyHero(@RequestBody Hero hero){
        hero.setId(100);
        System.out.println(validate(hero));
        return hero;
    }
    @RequestMapping("/{id}")
    Hero getHeroById(@PathVariable long id){
        return service.getHeroById(id);
    }
    private String validate(Hero hero){
        String msgErr = "";
        if (hero.getName().length() < 4
                || hero.getName().length() > 14)
            msgErr += "Некорректная длина имени героя\n";
        if(hero.getLevel() < 0
                || hero.getLevel() > 30)
            msgErr += "Некорректный уровень героя\n";
        return msgErr;
    }
}
