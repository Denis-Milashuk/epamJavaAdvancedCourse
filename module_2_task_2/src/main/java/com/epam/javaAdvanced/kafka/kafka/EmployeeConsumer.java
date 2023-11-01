package com.epam.javaAdvanced.kafka.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.epam.schema.employee.Employee;

@Component
public class EmployeeConsumer {

    @KafkaListener(topics = "${kafka.topic.employee.name}", autoStartup = "true")
    public void listen(Employee employee) {
        System.out.println("Consumed message:");
        System.out.println(employee.toString());
    }
}
