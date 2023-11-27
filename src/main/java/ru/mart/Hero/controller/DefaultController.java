package ru.mart.Hero.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Hidden
public class DefaultController {
    @RequestMapping("/")
    public ResponseEntity<Object> index()
    {
        return ResponseEntity.status(HttpStatus.FOUND)
                             .location(URI.create("http://localhost:8080/swagger-ui/index.html"))
                                          .build();
    }
}

//        return (new Date().toString());
