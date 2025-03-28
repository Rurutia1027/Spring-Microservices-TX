package com.cloud.payment.service.message.service;

public interface MessageQueueService {
    // Send a message to a specific topic or queue
    void sendMessage(String destination, String message);

    // Receive a message from a specific topic or queue
    String receiveMessage(String destination);

    // Set up the queue or topic for production (Kafka/ActiveMQ)
    void setupProducer(String destination);

    // Set up the queue or topic for consumption (Kafka/ActiveMQ)
    void setupConsumer(String destination);

    // Clean up
    void close();
}
