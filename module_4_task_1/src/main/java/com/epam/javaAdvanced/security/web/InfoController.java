package com.epam.javaAdvanced.security.web;

import com.epam.javaAdvanced.security.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InfoController {

    private final LoginAttemptService loginAttemptService;

    @GetMapping("/info")
    public String info() {
        return "MVC application";
    }

    @GetMapping("/about")
    public String about() {
        return "About endpoint";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint";
    }

    @GetMapping("/blocked")
    public String getBlocked() {
        return loginAttemptService.getBlocked().toString();
    }
}
