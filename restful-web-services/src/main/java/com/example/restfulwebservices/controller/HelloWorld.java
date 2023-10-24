package com.example.restfulwebservices.controller;

import com.example.restfulwebservices.dto.HelloWordBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWordBean helloWorldBean(){
        return new HelloWordBean("Hello World!");
    }
}
