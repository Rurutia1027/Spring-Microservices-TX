# Flexible Transaction Solution: Best-Effort Notification (Periodic Reconciliation)
![](02-BE-notification.png)

## Implementation 
- The business activity initiator proactively sends messages to the **business activity recipient** after completing business processing, allowing for possible message loss. 
- The **business activity recipient** queries the initiator periodically based on a strategy to **recover lost business messages**. 

## Commitments 
- The recipient's **processing results do not affect** the initiator's processing results. 

## Cost 
- Business **query and reconciliation system construction costs**. 

## Applicable Scenarios 
- Low sensitivity to final business consistency timing. 
- Cross-enterprise business activities. 


## Service Most Used 
- Query-based operations 

## Key Characteristics 
- The **initiator**(active party) sends a notification message to the **responder**(passive party) after completing a business process.(Message loss is allowed).
- The **initiator** can define **exponential retry rules**, meaning if a notification fails, it will be retried according to predefined intervals until it reaches **N attempts**, after which it stops. 
- The **initiator** provides an interface for reconciliation queries, allowing the **responder** to recover lost business messages by querying when necessary. 

## Industry Use Cases 
- Bank notifications 
- Merchant notifications (e.g., inter-platform transaction notifications, multiple retries, query-based reconciliation, and account reconciliation files). 


