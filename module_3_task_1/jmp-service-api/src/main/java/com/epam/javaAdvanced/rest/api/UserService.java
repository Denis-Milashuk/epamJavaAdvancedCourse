package com.epam.javaAdvanced.rest.api;

import com.epam.javaAdvanced.rest.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);
    List<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);
}
