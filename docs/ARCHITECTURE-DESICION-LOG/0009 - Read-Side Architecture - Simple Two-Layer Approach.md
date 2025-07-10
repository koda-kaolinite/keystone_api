## **Title: Read-Side Architecture: Simple Two-Layer Approach**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Having adopted the CQRS architectural style (see [[0006 - Applying CQRS within Modules||ADR 6]]), we now need to define the specific layered architecture for handling read operations (queries). The goal is to create a simple, performant, and easy-to-understand query stack.

---

### **Decision**

We will use a lean, **two-layered architecture** for the query-handling pipeline.

The two layers are:

1. **API Layer:** The controller receives the HTTP request, validates it, and constructs a specific `Query` object with the necessary parameters (e.g., filters, pagination info).
2. **Application Layer:** A dedicated `Query Handler` within the module receives the `Query` object. This handler is responsible for directly querying the database (using a simple data access method like Dapper or Entity Framework Core's projection capabilities), mapping the result to a DTO, and returning it.

This approach deliberately avoids additional layers of abstraction (like the Repository Pattern) on the read side, as the separate read model already simplifies data retrieval.

---

### **Consequences**

- **Simplicity & Maintainability**
    
    - The entire query-handling logic for a given query is contained within a single handler in the Application Layer.
    - The solution is simple and easy for developers to understand and maintain.
- **Performance**
    
    - Performance is optimized as there are minimal layers and no complex object-mapping between data, domain, and DTO layers. Queries access the database almost immediately.
- **Coupling & Trade-offs**
    
    - We make a conscious decision to **not** abstract the database on the read side. The Application Layer is intentionally coupled to the data-querying technology (e.g., SQL, a specific framework) to maximize performance and simplicity.
    - This is a pragmatic trade-off: we sacrifice data access abstraction on the read-side for a significant gain in performance and a reduction in complexity.