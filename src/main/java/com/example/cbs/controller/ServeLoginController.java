package com.example.cbs.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServeLoginController {
    @GetMapping("/hello")
    public String getIndex(){
        return "index";
    }
}
