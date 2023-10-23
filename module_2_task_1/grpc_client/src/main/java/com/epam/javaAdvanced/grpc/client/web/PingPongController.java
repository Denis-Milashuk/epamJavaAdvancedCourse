package com.epam.javaAdvanced.grpc.client.web;

import com.epam.javaAdvanced.grpc.PingPongRequest;
import com.epam.javaAdvanced.grpc.PingPongServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    @GrpcClient("pingPongService")
    private PingPongServiceGrpc.PingPongServiceBlockingStub pingPongClient;

    @GetMapping("/pingPong")
    public String pingPong(@RequestParam String message) {
        return this.pingPongClient.sayPong(PingPongRequest.newBuilder().setMessage("Ping " + message).build()).getMessage();
    }
}
