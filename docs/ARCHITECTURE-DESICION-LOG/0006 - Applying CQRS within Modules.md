## **Title: Architectural Pattern: Applying CQRS within Modules**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Our application needs to handle two fundamentally different types of operations: reads (queries) and writes (commands).

- **Write operations** require a rich domain model (often a graph of objects) to enforce complex business rules, validations, and calculations.
- **Read operations** often need to return data in a flattened or denormalized structure, optimized for display in lists, tables, and reports.

Using a single model for both responsibilities leads to compromises that make the model overly complex and inefficient for both tasks.

---

### **Decision**

We will formally adopt the **Command Query Responsibility Segregation (CQRS)** architectural pattern within each business module.

This means every module will have two distinct models:

1. **The Write Model (Command Side):** Uses rich domain entities and aggregates to process commands and enforce business rules.
2. **The Read Model (Query Side):** Uses simple, often denormalized data transfer objects (DTOs) tailored specifically for client queries.

Initially, we will implement the simplest form of CQRS: the read and write models will operate on the **same physical (Not Logic) database**, ensuring immediate consistency. This decision formalizes the command/query separation introduced in this ADR.

---

### **Consequences**

- **Optimized Model Design**
    
    - Each model adheres to the Single Responsibility Principle (SRP). The write model is concerned only with state changes and business logic, while the read model is concerned only with data retrieval.
    - This prevents the creation of bloated models that try to serve conflicting purposes.
- **Processing Flexibility**
    
    - We gain the flexibility to process commands and queries in different ways. For example, writes can go through a full domain model with complex logic, while reads can be handled by simple, highly-performant raw SQL queries or a micro-ORM like Dapper.
- **Clarity and Maintainability**
    
    - The intent of a piece of code is immediately clear from its type: a `Command` intends to change state, while a `Query` only retrieves data without side effects.
    - As Commands and Queries are explicit objects, they can be easily serialized for logging, auditing, or being placed on a queue for asynchronous processing in the future.
    - Testing is simplified, as command handlers and query handlers can be tested in isolation.