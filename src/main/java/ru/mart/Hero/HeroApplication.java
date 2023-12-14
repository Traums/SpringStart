package ru.mart.Hero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableJpaRepositories("ru.mart.Hero.repos")
public class HeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}
}
