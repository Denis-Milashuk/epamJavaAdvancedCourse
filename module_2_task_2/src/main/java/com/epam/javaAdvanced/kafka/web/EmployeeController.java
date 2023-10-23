package com.epam.javaAdvanced.kafka.web;

import com.epam.javaAdvanced.kafka.domain.dto.EmployeeDto;
import com.epam.schema.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    @Value("${kafka.topic.employee.name}")
    private String topic;

    @PostMapping
    public ResponseEntity<Object> postEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = Employee
                .newBuilder()
                .setId(employeeDto.getId().intValue())
                .setFirstName(employeeDto.getFirstName())
                .setLastName(employeeDto.getLastName())
                .build();

        kafkaTemplate.send(topic, employee);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }
}
