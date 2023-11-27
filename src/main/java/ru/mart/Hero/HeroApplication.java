package ru.mart.Hero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class HeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}
}
