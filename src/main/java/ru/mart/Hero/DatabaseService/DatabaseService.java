package ru.mart.Hero.DatabaseService;
import ru.mart.Hero.domain.Hero;
import java.util.List;
//I - Interface Segregation Principle
public interface DatabaseService {
    Hero saveHero(Hero hero);
    Hero getHeroById(Long id);
    List<Hero> getAll();
}
