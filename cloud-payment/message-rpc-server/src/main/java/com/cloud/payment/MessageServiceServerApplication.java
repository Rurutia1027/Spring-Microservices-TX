package com.cloud.payment;

import com.cloud.payment.interceptor.server.GrpcServerInterceptor;
import com.cloud.payment.server.service.MessageServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class MessageServiceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceServerApplication.class, args);
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${grpc.server.port}")
    private int port;

    private final MessageServiceImpl messageServiceImpl;

    @Autowired
    public MessageServiceServerApplication(final MessageServiceImpl messageServiceImpl) {
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