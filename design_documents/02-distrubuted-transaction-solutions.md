# Distributed Transaction Solutions

## Eventual Consistency Based on Reliable Messaging (Partial Guarantee Model)

![](./02-eventual-consistency-solution.png)

- This solution is widely adopt, and it is a lightweight solution as long as the business requirements do not strictly
  rely on real-time consistency.
- Decouples the system via async processing, primarily achieved through a message system that both decouples and
  connects different services within the system.

## TCC(Try-Confirm-Cancel) Transaction Compensation Solution
![](./02-tcc-solution.png)

- Another solution is the TCC transaction compensation mechanism. It is a variant of the two-phase commit(2PC)
  implementation but differs from traditional 2PC protocols in certain aspects.

- This solution is closely related to the technology stack and database access framework. Different technology choices(
  e.g., database types, data middleware like Hibernate, MyBatis) lead to variations in the implementation of TCC
  solutions and strategies.

## Best-Effort Notification Solution

![](02-BE-notification.png)

- This solution primarily used cross-platform communication for distributed transactions as a best practice. 