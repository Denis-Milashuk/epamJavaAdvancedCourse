package com.epam.javaAdvanced.rest.repository;

import com.epam.javaAdvanced.rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
