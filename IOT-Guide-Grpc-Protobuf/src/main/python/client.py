import grpc
import hello_pb2_grpc
import hello_pb2

channel = grpc.insecure_channel("localhost:8003")
stub = hello_pb2_grpc.HelloServiceStub(channel)
resp: hello_pb2.HelloResponse = stub.sayHello(hello_pb2.HelloRequest(name = "world"))
print("resp msg = {0}".format(resp.msg))