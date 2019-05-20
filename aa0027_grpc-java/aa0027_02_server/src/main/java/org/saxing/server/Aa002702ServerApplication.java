package org.saxing.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

public class Aa002702ServerApplication {

    private static final int port = 8180;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.
                forPort(port)
                .addService( new RPCDateServiceImpl() )
                .build().start();
        System.out.println( "服务端启动成功, 端口=" + port );
        server.awaitTermination();
    }

}
