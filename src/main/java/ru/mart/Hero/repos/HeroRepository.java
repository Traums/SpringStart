package ru.mart.Hero.repos;

import ru.mart.Hero.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero,Long> {
}
