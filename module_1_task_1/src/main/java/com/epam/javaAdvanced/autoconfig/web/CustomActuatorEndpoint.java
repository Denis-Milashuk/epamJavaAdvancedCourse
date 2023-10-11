package com.epam.javaAdvanced.autoconfig.web;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "custom")
public class CustomActuatorEndpoint {

    @ReadOperation
    public String customEndpoint() {
        return "customEndpoint";
    }
}


