package com.cloud.payment.server.config;

import com.cloud.payment.interceptor.server.GrpcServerInterceptor;
import com.cloud.payment.server.service.MessageServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GRpcServerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${grpc.server.port}")
    private int port;

    private final MessageServiceImpl messageServiceImpl;

    public GRpcServerConfig(final MessageServiceImpl messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }

    @Bean
    public Server grpcServer() throws IOException {
        Server server = ServerBuilder
                .forPort(port)
                .addService(messageServiceImpl)
                .intercept(new GrpcServerInterceptor(applicationName))
                .build();

        server.start();
        return server;
    }
}
