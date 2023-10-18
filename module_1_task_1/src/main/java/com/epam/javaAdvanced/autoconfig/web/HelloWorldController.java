package com.epam.javaAdvanced.autoconfig.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }
}
