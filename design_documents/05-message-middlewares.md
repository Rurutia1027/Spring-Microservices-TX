# Message Middlewares 

In distributed systems, message middleware plays different roles depending on the use case. Three classic scenarios highlight its importance: 

## Communication 
MEssage middleware serves as the bridge between active and passive applications, enabling seamless communication. It provides essential services and features to facilitate reliable data exchange across different components. 

## Decoupling 
One of the key reasons for adopting message middleware is to reduce tight coupling between system components. By breaking a complex system into smaller, independent modules and linking them flexibly, middleware prevents direct dependencies. Without this, even unrelated components - maintained by different teams - could impact each other during development, deploymnet, or refactoring, leading to failures and inefficiencies. 

## Concurrency and Caching
_Using Message Middleware to Resolve Concurrency Mismatch: Implementing a Concurrency Cache_

- We use **peak shaving** and **load leveling** techniques in message middleware to **buffer high concurrency traffic** and ensure that the receiving system processes requests at a stable rate, preventing overload and ensuring data accuracy. 

One critical reason for adopting message middleware is to **resolve concurrency mismatch** between the active and passive communication components in a distributed syste. 

For example, an active service may generate **10000 concurrent tasks** and send them to a passive service(e.g., a server or database). However, if the passive service can only handle **3 concurrent tasks** at a time, this mismatch leads to **queueing delays**. Over time if network failures, resource on the passive party exhaustion, or system bottlenecks occur, the passive service may start **dropping requests**, leading to **data loss**, **inconsistencies**, and even **SLA violations** -- especially in mission-critical scenarios like financial transactions. 

By introducing **message middleware**, we can buffer incoming tasks instead of relying solely on the passive services' thread pool. The message broker holds all incoming **10000 tasks** and feeds them to the passive service at its processing capacity (3 at a time). This ensures that: 
- No tasks are lost due to concurrency mismatches. 
- Processing happens at an optimal rate without overwhelming the passive service. 
- Retried or failed tasks are managed efficiently without causing unnecessary resource overhead. 

--- 

## Message Consistency in Distributed Systems 
Message consistency in distributed systems, which ensures that messages and business logic operations are executed in sync-either both succeed or both fail. This is a critical topic in **message middleware** because inconsistency can lead to data **corruption**, **duplication**, or **loss** in distributed architectures. 


## How to Guarantee Message Consistency in Distributed Systems?


### Case-1: Message & Logic Status Consistency Cannot Guarantee 
```java
// order purchase logic 
public void completeOrder() {
    // Handle order business logic 
    orderBiz.process(); 

    // Dispatch accounting Original Voucher Message/Dispatch Message 
    sendAccountingVoucherMsg(); 
}
```
Two scenarios can cause inconsistencies between **Messages** and **Business Logic**: 

- If the business operation succeeds, but the application (in the order system scope) fails before sending the message to the accounting system, the message is blocked, causing data inconsistency between two communication peers - Order System & Accounting System. 

- If the business operation succeeds in the order system side, but the messaging system fails, the message is lost too, leading to mismatched records between the **Order System** and **Accounting System**.


### Case-2: Message & Logic Status Consistency Cannot Guarantee Either 

```java
// order processing function 
public void completeOrder() {
    // send message that record the status of the logic to Accounting System first/before the exeuction of business logic
    sendAccountingVoucherMsg(); 

    // then process the business logic 
    orderBiz.process(); 
}
```

This solution cannot guarantee consistency between business logic and messages. For example, if a message is sent to the Accounting System but an error during local business logic execution, it can lead to a mismatch between the final state of the Order System and the status in the Accounting System. 

