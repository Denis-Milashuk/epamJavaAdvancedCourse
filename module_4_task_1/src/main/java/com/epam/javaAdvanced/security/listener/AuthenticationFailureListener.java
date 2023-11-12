package com.epam.javaAdvanced.security.listener;

import com.epam.javaAdvanced.security.repo.UserRepository;
import com.epam.javaAdvanced.security.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {

    private final LoginAttemptService loginAttemptService;
    private final UserRepository userRepository;

    @EventListener
    public void onBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof String userName) {
            if (userRepository.findByUserName(userName).isPresent()) {
                loginAttemptService.loginFailed(userName);
            }
        }
    }
}
