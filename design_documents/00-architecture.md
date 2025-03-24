# 00-Architecture

## System Architecture Diagram

![](./00-architecture-diagram.png)

## Payment Platform & Bank Interaction Diagram

![](./00-payment-platform-bank-interact.png)

- **Step1**: The payment gateway verifies the bank notification result and then calls the payment order service to
  process the payment order.
- **Step2**: The payment order service updates the payment order status based on the bank deduction result.
- **Step3**: The funds account service credits the merchant’s account (this may involve various cost calculations; if
  paying from a balance, it may deduct from the user’s account while crediting the merchant’s account at the same time).
- **Step4**:  The points service increases the user’s reward points.
- **Step5**: The accounting service records the transaction and generates accounting entries.
- **Step6**: The notification service informs the payment processing result to relevant platforms.