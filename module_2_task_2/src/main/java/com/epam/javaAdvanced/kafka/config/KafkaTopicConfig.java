package com.epam.javaAdvanced.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaTopicConfig {

    @Value("${kafka.bootstrap-server}")
    private String bootstrapAddress;

    @Value("${kafka.topic.employee.name}")
    private String topic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        log.debug("Created KafkaAdmin with bootstrapAddress : {}", bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        NewTopic topic =  new NewTopic(this.topic, 1, (short) 1);
        log.debug("Kafka topic: {} was created", topic);
        return topic;
    }

}