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

