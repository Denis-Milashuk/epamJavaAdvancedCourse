package com.epam.javaAdvanced.grpc.server.service;

import com.epam.javaAdvanced.grpc.PingPongRequest;
import com.epam.javaAdvanced.grpc.PingPongResponse;
import com.epam.javaAdvanced.grpc.PingPongServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PingPongService extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void sayPong(PingPongRequest request, StreamObserver<PingPongResponse> responseObserver) {
        String message = request.getMessage();
        PingPongResponse response = PingPongResponse.newBuilder()
                .setMessage(String.format("Pong message: %s", message))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
