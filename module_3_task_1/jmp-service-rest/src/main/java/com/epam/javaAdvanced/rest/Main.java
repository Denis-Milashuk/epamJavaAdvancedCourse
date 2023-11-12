package com.epam.javaAdvanced.rest;

import com.epam.javaAdvanced.rest.api.SubscriptionService;
import com.epam.javaAdvanced.rest.api.UserService;
import com.epam.javaAdvanced.rest.domain.Subscription;
import com.epam.javaAdvanced.rest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {


    private final SubscriptionService subscriptionService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        User user = new User()
                .setName("Dima")
                .setSurname("Milashuk")
                .setBirthday(LocalDate.of(2017, 6, 14));

        Subscription subscription = new Subscription()
                .setUser(user)
                .setStartDate(LocalDate.now());

        subscriptionService.save(subscription);
    }
}