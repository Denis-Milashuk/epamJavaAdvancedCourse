package com.epam.javaAdvanced.autoconfig.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SomeTestServiceImpl implements SomeTestService {
    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
}
