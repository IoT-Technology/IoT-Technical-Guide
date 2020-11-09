package iot.technology.grpc.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class GrpcProtobufServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8003)
                .addService(new HelloServiceImpl())
                .build()
                .start();
        log.info("gRPC server started on port 8003");
        server.awaitTermination();
    }
}
