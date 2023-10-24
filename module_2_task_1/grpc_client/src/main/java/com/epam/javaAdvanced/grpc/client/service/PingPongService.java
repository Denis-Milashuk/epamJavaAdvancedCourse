package com.epam.javaAdvanced.grpc.client.service;

import com.epam.javaAdvanced.grpc.PingPongRequest;
import com.epam.javaAdvanced.grpc.PingPongServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class PingPongService {

    @GrpcClient("pingPongService")
    private PingPongServiceGrpc.PingPongServiceBlockingStub pingPongClient;

    public String sayPong(String message) {
        return this.pingPongClient.sayPong(PingPongRequest.newBuilder().setMessage("Ping " + message).build()).getMessage();
    }
}
