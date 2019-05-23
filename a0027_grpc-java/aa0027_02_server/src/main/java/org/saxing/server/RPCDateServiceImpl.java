package org.saxing.server;

import org.saxing.grpc.api.RPCDateRequest;
import org.saxing.grpc.api.RPCDateResponse;
import org.saxing.grpc.api.RPCDateServiceGrpc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * rpcdate
 *
 * @author saxing 2019/5/20 23:33
 */
public class RPCDateServiceImpl extends RPCDateServiceGrpc.RPCDateServiceImplBase {

    @Override
    public void getDate(RPCDateRequest request, io.grpc.stub.StreamObserver<RPCDateResponse> responseObserver) {
        RPCDateResponse rpcDateResponse = null;
        try {
            rpcDateResponse = RPCDateResponse
                    .newBuilder()
                    .setServerDate("Welcome " + request.getUserName() + ", " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .build();
        } catch (Exception e){
            responseObserver.onError(e);;
        } finally {
            responseObserver.onNext(rpcDateResponse);
        }
        responseObserver.onCompleted();
    }
}
