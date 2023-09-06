package ru.mart.Hero;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"ru.mart.*"})
//@ComponentScan(basePackages = "ru.mart");
public class HeroApplication {
	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}
//	@Bean
//	public JsonDeserializer jsonDeserializer() {
//		return new JsonDeserializer() {
//			@Override
//			public Object deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
//				return null;
//			}
//		};
//	}
//
//	@Bean
//	public ObjectMapper objectMapper() {
//		return new ObjectMapper();
//	}
}
