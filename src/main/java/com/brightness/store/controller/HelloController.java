package com.brightness.store.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostConstruct
    public void init() {
        System.out.println(">>> HelloController CARGADO <<<");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hola desde Brightness Store API";
    }
}
