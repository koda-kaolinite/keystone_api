## **Title: Domain Implementation with DDD Tactical Patterns**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Having committed to Clean Architecture ([[0010 - Applying Clean Architecture on the Write-Side||ADR 10]]) and the use of Rich Domain Models ([[0011 - Domain Modeling Style - Rich Domain Model||ADR 11)]], we need a common vocabulary and a set of proven building blocks to implement our Domain Layer. This ensures that the business logic is modeled consistently and effectively across all modules.

---

### **Decision**

We will officially adopt and use the **tactical patterns of Domain-Driven Design (DDD)** as the foundational elements for constructing our Rich Domain Models.

The specific building blocks we will use are:

- **Aggregate:** A cluster of related domain objects (Entities and Value Objects) that are treated as a single unit for data changes. The **Aggregate Root** (a specific Entity) is the single entry point to the cluster, ensuring that all business rules and invariants are protected.
- **Entity:** A domain object with a distinct, continuous identity that is not defined by its attributes (e.g., a `Property` with a unique ID).
- **Value Object:** An immutable domain object that has no conceptual identity and is defined purely by its attributes.
- **Domain Event:** An immutable object that represents a significant business fact that occurred in the past. It's used to communicate changes between different parts of the system.
- **Repository:** A collection-like interface used by the Application Layer to persist and retrieve Aggregates, abstracting away the underlying data storage mechanism.
- **Domain Service:** A stateless service used to encapsulate business logic that does not naturally belong to any single Entity or Value Object.

---

### **Consequences**

- **Design & Implementation**
    
    - We must explicitly define the boundaries for each Aggregate, which determines the scope of transactional consistency.
    - A Repository will be created for each Aggregate Root.
    - The principle of encapsulation is paramount: external clients (like the Application Layer) can _only_ interact with the Aggregate through the public methods on its Root.
- **Complexity & Maintainability**
    
    - While providing a robust structure, these patterns introduce a higher level of abstraction that must be managed.
    - The explicit modeling leads to a domain layer that is easier to understand, maintain, and align with business requirements.
- **Team & Skills**
    
    - All developers working on the write-side of the system must be familiar with and consistently apply these DDD tactical patterns.