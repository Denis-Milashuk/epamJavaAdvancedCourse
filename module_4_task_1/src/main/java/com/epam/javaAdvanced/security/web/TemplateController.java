package com.epam.javaAdvanced.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TemplateController {

    @GetMapping("/login")
    public String login(final ModelMap model, @RequestParam("error") final Optional<String> error) {
        error.ifPresent(err -> model.addAttribute("error", err));

        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logout() {
        return "logout";
    }
}
