package com.cloud.payment.web.config;

import com.cloud.payment.interceptor.client.GrpcClientInterceptor;
import com.cloud.payment.service.message.grpc.RpcRpTransactionMessageServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MessageServiceConfig {
    @Value("${grpc-client.message-service.host}")
    private String grpcHost;

    @Value("${grpc-client.message-service.port}")
    private int grpcPort;

    private static final String GRPC_CLIENT_NAME = "greet-client";
    private static final String GRPC_SERVER_NAME = "greet-server";

    @Bean(name = "managedChannelMessageService")
    public ManagedChannel managedChannelMessageService(final Tracer tracer) {
        return ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .idleTimeout(20, TimeUnit.MINUTES)
                .intercept(new GrpcClientInterceptor(GRPC_CLIENT_NAME, GRPC_SERVER_NAME, tracer))
                .build();
    }

    @Bean
    public RpcRpTransactionMessageServiceGrpc.RpcRpTransactionMessageServiceBlockingStub
    messageServiceBlockingStub(final ManagedChannel managedChannel) {
        return RpcRpTransactionMessageServiceGrpc.newBlockingStub(managedChannel);
    }
}
