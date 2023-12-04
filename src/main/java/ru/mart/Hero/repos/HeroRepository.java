package ru.mart.Hero.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ru.mart.Hero.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mart.Review.Client.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero,Long> {

    @Query("FROM Hero h where h.name = ?1 order by h.id LIMIT 1")
    Hero findIdByFirstname(String firstname);

    @Procedure("GET_TOTAL_HEROES")//@Query(value = "CALL GET_TOTAL_HEROES(:model)", nativeQuery = true)//
    int getTotalHeroes(@Param("input") String model);
}
