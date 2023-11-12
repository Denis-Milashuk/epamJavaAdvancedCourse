package com.epam.javaAdvanced.security.service;

import com.epam.javaAdvanced.security.domain.UserEntity;
import com.epam.javaAdvanced.security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DaoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserName(username);
        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        if (loginAttemptService.isBlocked(username)) {
            throw new LockedException("User is blocked");
        }

        UserEntity userEntity = userEntityOptional.get();

        return User
                .withUsername(userEntity.getUserName())
                .password(userEntity.getUserPassword())
                .authorities(userEntity.getUserAuthorities().split(";"))
                .build();
    }
}
