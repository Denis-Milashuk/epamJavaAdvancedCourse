package com.epam.javaAdvanced.autoconfig.repository;

import com.epam.javaAdvanced.autoconfig.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
