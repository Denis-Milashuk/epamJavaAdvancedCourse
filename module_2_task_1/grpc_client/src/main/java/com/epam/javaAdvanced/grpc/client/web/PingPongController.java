package com.epam.javaAdvanced.grpc.client.web;

import com.epam.javaAdvanced.grpc.client.service.PingPongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PingPongController {

    private final PingPongService pingPongService;

    @GetMapping("/pingPong")
    public String pingPong(@RequestParam String message) {
        return this.pingPongService.sayPong(message);
    }
}
