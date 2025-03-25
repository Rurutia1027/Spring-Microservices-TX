# Flexible Transactions V2
## Eventual Consistency Solution Based on Reliable Messaging
![](./02-eventual-consistency-solution.png)

### Implementation 
- Before committing a business transaction, the **business process service** sends a message to the **real-time message service.**
- The **real-time message service** only stores the message without sending it out. 
- After committing the transaction, the **business process service** confirms the message, triggering the **real-time message service** to send it out. 

### Handling Rollback 
- If the business transaction **rolls back**, it sends a **cancel message** to the **real-time message service**.
- The message status must be confirmed within a specified time. 
- If no confirmation or rollback is found, the **real-time message service** will actively check with the **business process service** to determine the actual status. 

### Idempotency & Processing Constraints
- The **dependent system's result does not affect the main system's result**.
- Message operations must be **idempotent**, meaning repeated execution yields the same result as the first request. 

### Cost & Complexity 
- Requires additional **resources** to maintain a reliable messaging system. 
- Involves **higher architectural complexity**, with messages needing **two requests**: one for **confirmation** and another for **notification**. 
- Requires business services to **implement interfaces** for handling message processing. 


### Advantages & Suitable Scenarios 
- **Scalable**: Message systems can dynamically adjust resources based on traffic. 
- **Decoupleing**: Helps **separate tightly coupled services**, allowing **asynchronous processing**.
- **Reliability**: Ensures message integrity through **confirmation and notification** mechanisms. 
- **Use Case**: Suitable for scenarios where **evetual consistency** is acceptable but **strict real-time consistency is not required**.
