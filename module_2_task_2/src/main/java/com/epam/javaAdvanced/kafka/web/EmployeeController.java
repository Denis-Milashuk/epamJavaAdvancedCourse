package com.epam.javaAdvanced.kafka.web;

import com.epam.javaAdvanced.kafka.domain.dto.EmployeeDto;
import com.epam.schema.employee.Employee;
import io.confluent.kafka.schemaregistry.client.SchemaMetadata;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    @Value("${kafka.topic.employee.name}")
    private String topic;
    @Autowired
    private SchemaRegistryClient schemaRegistryClient;

    @PostMapping
    public ResponseEntity<Object> postEmployee(@RequestBody EmployeeDto employeeDto) throws RestClientException, IOException {
        SchemaMetadata latestSchemaMetadata = schemaRegistryClient.getLatestSchemaMetadata(String.format("%s-value", topic));

        Employee employee = Employee
                .newBuilder()
                .setId(employeeDto.getId().intValue())
                .setFirstName(employeeDto.getFirstName())
                .setLastName(employeeDto.getLastName())
                .setSchemaId(latestSchemaMetadata.getId())
                .setSchemaVersion(latestSchemaMetadata.getVersion())
                .build();

        kafkaTemplate.send(topic, employee).join();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }
}
