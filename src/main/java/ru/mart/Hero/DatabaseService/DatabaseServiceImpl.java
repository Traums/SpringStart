package ru.mart.Hero.DatabaseService;


import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import ru.mart.Hero.domain.Hero;
import ru.mart.Hero.repos.HeroRepository;

import java.util.List;
@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService {
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
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(new Hero());
    }

    @Override
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }
}
