# Transaction 
A transaction is a sequence of database operations that are executed as a single unit. It ensures **data integrity** and **consistency**, even in the case of system failures. 


## What's ACID ? 

ACID stands for four key properties of a transaction: 

- **Atomicity** - All operations in a transaction succeed or **none do**(all-or-nothing). 
- **Consistency** - The database remains in a **valid state** before and after the transaction. 
- **Isolation** - Transactions do not **interfere** with each other (no partial updates seen).
- **Durability** - Once committed, the transaction's changes are **permannent**, even if the system crashes. 

--- 

## Local Transactions 
A **local transaction** is managed within a single **Relational Database Management System**(RDBMS) or **Database Management System**(DBMS). 

#### Advantages of Local Transactions 
- **Strict ACID Compliance** - Local transactions strictly adhere to **Atomicity**, **Consistency**, **Isolation**, and **Durability**(ACID), ensuring **data integrity** and **reliability**. 
- **Efficient** & **Simple** -- Since transaction states are maintained **only within the DBMS**, the implementation is straightforward and highly efficient. 

#### Limitation of Local Transactions 
- **No Support for Distributed Transactions** - Local transactions are limited to **single database instance** and cannot handle **transactions across multiple databases or services**. 
- **Resource Manager Dependency** - The smallest **unit of isolation** is managed by the *resource manager**, restricting flexibility in multi-database environments. 
- **Process Boundaries** - A local transaction is confined **within a single process**, making it unsuitable for **distributed architectures** where multiple services interact with different databases. 
  

In summary, while **local transactions** are simple and reliable for **single-database operations**, they cannot maintain **ACID properties across multiple datasources**, making them unsuitable for **microservices and distributed systems**. 

--- 

## Global Transactions in the DTP Model: A Standard Approach to Distributed Transactions
### AP (Application Program)
- The core application program that interacts with the DTP(Distributed Transaction Processing) model. It executes business logic and communicates with both the **transaction manager(TM)** and **resource manager(RM)** to manage transactions. 

### RM (Resource Manager)
- Manages access to actual resources like **databases(DBMS)**, or **message queues**. 
- Application programs interact with the RM to read and write data. 
- Must implement the **XA protocol**, ensuring compliance with distributed transaction principles. 

### TM (Transaction Manager)
- **Coordinates** and **manages transactions**, ensuring consistency across multiple RMs. 
- Provides transaction-related APIs for **applications**(APs) and controls the interaction between APs and RMs. 
- Handles **commit** and **rollback** decisions to maintain **data integrity** in distributed environments. 


### XA Protocol 
- The **industry standard** for distributed transactions, defining how a TM interacts with RMs. 
- Supports **Two-Phase Commit**(2PC), ensuring that all involved resources either **commit successfully** or **roll back** in case of failure. 

--- 


## Deep Dive into XA in DTP Model 

### XA and Its Origins 
XA is a standard published by the X/Open Distributed Transaction Processing (DTP) community, based on distributed transaction principles. It defines the interfaces between **Global Transaction Manager(TM)** and **Local Resource Managers(RM)**. Most popular database products support XA and implement its defined interfaces. 

### Bidirectional Interface for Coordination 
XA provides a bidirectional system interface that acts as a communication bridge between the **Transaction Manager(TM)** and one or more **Resource Managers(RM)**. This interface ensures seamless coordination between them to maintain transactional integrity. 

### The Need for XA in Distributed Systems 
In a distributed system, achieving a centrialized state across multiple machines is theoretically impossible. XA helps solve this issue by introducing a **centralized control point** through the **Transaction Manager**, which synchronizes transaction states across distributed resources. 

### Managing Distributed Transactions with 2PC 
Transactions managed by a **Global Transaction Manager** can span multiple resources, such as **databases**, **message queues**, and **processes**. XA typically relies on the **Two-Phase Commit(2PC) protocol** to ensure consistency across these distributed resources. 


## Introduction to the Two-Phase Commit (2PC) Protocol 
- 2PC as XA's Coordination Strategy
The **Two-Phase Commit(2PC)** protocol is the coordination mechanism used by **XA** to manage multiple resource managers(RMs) in a **global transaction**. It ensures that all involved resources either commit or roll back changes as a single unit. 

- Resolving Inconsistencies with 2PC 
The **Transaction Manager(TM)** and **Resource Managers(RMs)** use **2PC** to address potential **mechanical inconsistencies** that may arise during distributed transactions, ensuring data consistency across all participating nodes. 

- The Role of a Coordinator in 2PC 
In **2PC**, a **coordinator (typically the TM)** is responsible for managing all participating **Resource Managers(RMs)**. It ensures that each **RM** reaches a decision on whether to commit or roll back its operations and synchronizes the final transaction outcome accordingly. 

---

## Pros and Cons of Standard/Tranditional Distributed Transaction Solutions 

### Pros
- Strict ACID Compliance: Standard distributed transaction solutions **strictly follow ACID principles**, ensuring data consistency, reliability, and integrity across multiple resource managers. 

### Cons

#### Low Performance and Inefficiency 
- 2PC (Two-Phase Commit) protocol, which is used in **XA-based transactions**, causes significant performance overhead.
- Transactions **lock resources** (e.g., database rows) until completion, leading to poor efficiency. 
- This makes it **unsuitable for modern microservices architectures**, which prioritize scalability and high performance. 

#### Limited Scalability 
- **2PC** is not a scalable model because participants must hold resources until the transaction is finalized. 
- As business complexity increases, the overhead of maintaining global transactions grows, leading to **bottlenecks** and **system inefficiencies**.

#### High Overhead && Limited Compatibility 
- **XA-based transactions introduce signficant operational overhead**, requiring careful consideration before adoption. 
- Only **XA-compatible** resources can participate, meaning **existing systems may require extensive modifications** to support XA transactions. 