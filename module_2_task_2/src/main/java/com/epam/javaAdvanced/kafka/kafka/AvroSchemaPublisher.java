package com.epam.javaAdvanced.kafka.kafka;

import com.epam.schema.employee.Employee;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.serializers.subject.TopicNameStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Slf4j
public class AvroSchemaPublisher {

    @Autowired
    private SchemaRegistryClient schemaRegistryClient;

    @Value("${kafka.topic.employee.name}")
    private String topic;

    @PostConstruct
    public void init() {
        AvroSchema schema = new AvroSchema(Employee.SCHEMA$);
        String subj = new TopicNameStrategy().subjectName(topic, false, schema);
        try {
            schemaRegistryClient.register(String.format(subj, topic), schema);
            log.info("Avro schema registered. Subject:{}", subj);
        } catch (Exception ex) {
            log.error("Failed to register schema. Subject: {}", subj);
            throw new RuntimeException(ex);
        }
    }
}
