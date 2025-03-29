# Queue Message Model in Message Queues (MQ)

## Message Producer & Consumer Interaction 
- The **producer** sends messages to the queue. 
- The **consumer** listens to the queue and processes incoming messages. 

## Message Acknowledgment & Removal 
- Once a message is **acknowledged** by the consumer, it gets removed from the queue. 
- This ensures that the same message **won't be reprocessed** by another consumer. 

## Multiple Consumers, Single Consumption 
- The queue **supports multiple consumers**, but a single message is consumed by **only one consumers**.
- If multiple consumers are available, the message is delivered to only **one of them** (based on a **load-balancing mechanism**).

## Additional Considerations 
- **Message Durability**: Some MQ's allow messages to persiste even if the broker restarts (e.g., RabbitMQ's durable queues).
- **Dead Letter Queue(DLQ)**: if a message fails to be processed, it can be moved to a **dead letter queue** for further investigation.
- **FIFO** vs. **Standard Queue**: Some MQs(like Amazon SQS) support **FIFO**(First-In-First-Out) queues, while others use standard delivery mechanisms. 