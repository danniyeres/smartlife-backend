package org.example.smartlifebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/welcome")
public class Controller {

    @GetMapping
    public String hello() {
        return "Hello, Smartlife!";
    }
}
