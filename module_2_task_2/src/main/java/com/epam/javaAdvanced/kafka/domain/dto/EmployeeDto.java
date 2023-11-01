package com.epam.javaAdvanced.kafka.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
}
