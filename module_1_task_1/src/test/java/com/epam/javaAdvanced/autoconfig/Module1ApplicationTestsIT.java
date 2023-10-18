package com.epam.javaAdvanced.autoconfig;

import com.epam.javaAdvanced.autoconfig.entity.TestEntity;
import com.epam.javaAdvanced.autoconfig.repository.TestEntityRepository;
import com.epam.javaAdvanced.autoconfig.service.SomeTestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
class Module1ApplicationTestsIT {

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        TestEntity testEntity = new TestEntity()
                .setName("testName");

        TestEntity save = testEntityRepository.save(testEntity);

        Assertions.assertEquals(testEntity.getName(), save.getName());
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(SomeTestService.class));
    }
}
