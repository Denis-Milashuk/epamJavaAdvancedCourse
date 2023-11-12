package com.epam.javaAdvanced.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.secret.salt}")
    private String salt;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationFailureHandler authenticationFailureHandler) throws Exception {
        return httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers("/about", "/login*", "/blocked").permitAll()
                            .requestMatchers("/info").hasAuthority("VIEW_INFO")
                            .requestMatchers("/admin").hasAuthority("VIEW_ADMIN")
                            .anyRequest().authenticated();
                })
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureHandler(authenticationFailureHandler)
                        .permitAll())
                .logout(logout -> {
                    logout
                            .deleteCookies("JSESSIONID")
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/logoutSuccess")
                            .permitAll();
                })
                .build();
    }

    @Bean
    public PasswordEncoder cryptPasswordEncoder() {
        return new StandardPasswordEncoder(salt);
    }

}
