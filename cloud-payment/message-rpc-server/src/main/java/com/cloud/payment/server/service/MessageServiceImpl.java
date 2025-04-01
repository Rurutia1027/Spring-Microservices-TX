package com.cloud.payment.server.service;

import com.cloud.payment.service.message.grpc.RpcRpTransactionMessageServiceGrpc;
import com.cloud.payment.service.message.grpc.SaveMessageRequest;
import com.cloud.payment.service.message.grpc.SaveMessageResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class MessageServiceImpl extends RpcRpTransactionMessageServiceGrpc.RpcRpTransactionMessageServiceImplBase {
    @Override
    public void saveMessageWaitingConfirm(SaveMessageRequest request,
                                          StreamObserver<SaveMessageResponse> responseObserver) {
        // Extract the message from the request
        if (Objects.nonNull(request) && Objects.nonNull(request.getMessage())) {
            System.out.println(request.getMessage().getConsumerQueue());

        } else {
            System.out.println("Received null message request");
        }

        // Build the response
        SaveMessageResponse.Builder responseBuilder = SaveMessageResponse.newBuilder();

        // Set the status field to indicate the result of processing
        // You can modify this logic to set different statuses based on your application logic
        int status = 0; // For example, 0 could indicate success, you can change this to match your business logic
        responseBuilder.setStatus(status);

        // Create the response message
        SaveMessageResponse response = responseBuilder.build();

        // Send the response back to the client
        responseObserver.onNext(response);

        // Indicate that the processing is complete
        responseObserver.onCompleted();
    }
}
