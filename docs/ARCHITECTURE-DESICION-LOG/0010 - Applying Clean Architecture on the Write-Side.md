## **Title: Write-Side Architecture: Applying Clean Architecture**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Having adopted the CQRS pattern ([[0006 - Applying CQRS within Modules||ADR 6]]) and defined a simple, lean architecture for the read side ([[0009 - Read-Side Architecture - Simple Two-Layer Approach||ADR 9]]), we must now define the architecture for handling write operations (Commands).

Unlike queries, commands often trigger complex business rules, validations, and state changes. Therefore, the write-side architecture must prioritize correctness, testability, and the strict encapsulation of domain logic.

---

### **Decision**

We will use the **Clean Architecture** style for the command-handling pipeline. This introduces a clear separation of concerns through four distinct layers, with the core principle that dependencies always point inwards toward the Domain.

The layers are:

1. **Domain Layer:** The core of the architecture. It contains the business entities, aggregates, and rich domain logic. This layer has zero dependencies on any other layer.
2. **Application Layer:** Orchestrates the business workflows. It contains the `Command Handlers` which use the Domain Layer to perform tasks. It defines abstractions (interfaces) for infrastructure concerns like persistence.
3. **API Layer:** The entry point. Its sole responsibility is to receive HTTP requests, create `Command` objects, and pass them to the Application Layer for processing.
4. **Infrastructure Layer:** Implements the abstractions defined in the Application Layer (e.g., Repositories using Entity Framework, message bus clients, etc.). It handles all communication with external systems like the database.

This structure isolates the core business logic from all external concerns, making it robust and maintainable.

---

### **Consequences**

- **Architectural Benefits**
    
    - **Testability:** The Domain Layer can be tested in complete isolation, without a database or any infrastructure, leading to fast and reliable unit tests.
    - **Loose Coupling:** The Application and Domain layers depend only on abstractions, not concrete implementations, making the system flexible and easier to change.
    - **Persistence Independence:** The core business logic is independent of the database technology. We can change from SQL Server to another database with minimal impact on the Domain and Application layers.
- **Complexity & Separation of Concerns**
    
    - The overall solution complexity is higher due to the introduction of the Domain and Infrastructure layers.
    - However, the complexity _within_ each layer is significantly lower, as each has a single, clear responsibility.
    - Business logic is cleanly separated from infrastructure concerns.
- **Developer Experience**
    
    - This is a well-known and widely adopted architecture, making it familiar to many developers and easier to onboard new team members.