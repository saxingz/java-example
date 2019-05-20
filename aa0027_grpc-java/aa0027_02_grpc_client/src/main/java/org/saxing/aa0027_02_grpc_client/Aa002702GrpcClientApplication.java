package org.saxing.aa0027_02_grpc_client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.saxing.grpc.api.RPCDateRequest;
import org.saxing.grpc.api.RPCDateResponse;
import org.saxing.grpc.api.RPCDateServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aa002702GrpcClientApplication {

    private static final String host = "localhost";
    private static final int serverPort = 8180;

    public static void main( String[] args ) throws Exception {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress( host, serverPort ).usePlaintext().build();
        try {
            RPCDateServiceGrpc.RPCDateServiceBlockingStub rpcDateService = RPCDateServiceGrpc.newBlockingStub( managedChannel );
            RPCDateRequest rpcDateRequest = RPCDateRequest
                    .newBuilder()
                    .setUserName("saxing")
                    .build();
            RPCDateResponse rpcDateResponse = rpcDateService.getDate( rpcDateRequest );
            System.out.println( rpcDateResponse.getServerDate() );
        } finally {
            managedChannel.shutdown();
        }
    }

}
