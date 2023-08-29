package ru.mart.Hero.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.repos.HeroRepository;

import java.util.List;
@Service
@Transactional
public class DatabaseServiceImpl implements ru.mart.Hero.service.DatabaseService {
    private final HeroRepository heroRepository;
    @Autowired
    public DatabaseServiceImpl(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(new Hero());
    }

    @Override
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }
}
