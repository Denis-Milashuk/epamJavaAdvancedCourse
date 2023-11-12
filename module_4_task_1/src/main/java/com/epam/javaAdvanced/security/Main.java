package com.epam.javaAdvanced.security;

import com.epam.javaAdvanced.security.domain.UserEntity;
import com.epam.javaAdvanced.security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {

    @Value("${app.secret.salt}")
    private String salt;

    private final UserRepository userRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner () {
        return args -> {
            UserEntity user1 = new UserEntity()
                    .setUserName("User1")
                    .setUserPassword(new StandardPasswordEncoder(salt).encode("password"))
                    .setUserAuthorities("User");

            UserEntity user2 = new UserEntity()
                    .setUserName("User2")
                    .setUserPassword(new StandardPasswordEncoder(salt).encode("password"))
                    .setUserAuthorities("VIEW_INFO");

            UserEntity user3 = new UserEntity()
                    .setUserName("User3")
                    .setUserPassword(new StandardPasswordEncoder(salt).encode("password"))
                    .setUserAuthorities("VIEW_ADMIN;VIEW_INFO");

            userRepository.saveAll(List.of(user1, user2, user3));
        };
    }
}