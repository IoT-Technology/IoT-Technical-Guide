package iot.technology.grpc.protobuf;

import io.grpc.stub.StreamObserver;
import iot.technology.grpc.protobuf.proto.Hello;
import iot.technology.grpc.protobuf.proto.HelloServiceGrpc;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(Hello.HelloRequest request, StreamObserver<Hello.HelloResponse> responseObserver) {
        log.info("hello request, name = " + request.getName());
        Hello.HelloResponse response = Hello.HelloResponse.newBuilder()
                .setMsg("hello, " + request.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
