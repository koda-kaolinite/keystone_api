## **Title: Domain Modeling Style: Rich Domain Model**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Having committed to a Clean Architecture for our write side ([[0010 - Applying Clean Architecture on the Write-Side||ADR 10]]), we must now define the specific style for creating our Domain Models. The chosen approach will dictate how business logic is implemented, encapsulated, and maintained within the core Domain Layer of each module.

We considered three primary approaches:

1. **Anemic Domain Model:** Using simple data-holder objects (POCOs/POJOs) with business logic located in external service classes.
2. **Database-Centric Logic:** Placing all business logic within database stored procedures.
3. **Rich Domain Model:** Placing data and the relevant business logic that operates on that data together within the same objects.

---

### **Decision**

We will exclusively use a **Rich Domain Model** for implementing the write-side of our business modules.

This decision is driven by the anticipated complexity of our business logic. A Rich Domain Model allows us to fully leverage Object-Oriented Design principles (encapsulation, polymorphism, abstraction) to create a model that is both expressive and robust.

- **Anemic Models were rejected** because they lead to procedural code and scatter business logic across service classes, making it difficult to find and maintain.
- **Stored Procedures were rejected** because Object-Oriented code is far better suited for modeling complex domains than T-SQL, and we have no performance drivers that would justify sacrificing the benefits of OOD.

By creating rich domain objects, we ensure that the state of our objects can only be mutated through explicit methods. This encapsulates the logic, protects business invariants, and hides implementation details from the client layers (like the Application Service Layer).

---

### **Consequences**

- **Design & Encapsulation**
    
    - Domain objects will follow a "private by default" principle. State is encapsulated, and all modifications are exposed through a clear API of public methods.
    - This makes it much easier to protect business rules and invariants, as the object itself is responsible for maintaining its own consistency.
    - Clients of the Domain Model (e.g., Application Services) become simpler, as they are orchestrating calls to a self-contained, logical model.
- **Implementation & Skill Requirements**
    
    - Implementing a true Rich Domain Model requires a higher level of Object-Oriented Design skills from the development team.
    - There will be more upfront work, especially in the Infrastructure layer, to handle mapping data to and from encapsulated objects with private fields and collections.
- **Testability**
    
    - While business logic becomes highly testable, there can be a trade-off between pure encapsulation and the ease of testing specific internal states of an object. This requires a thoughtful approach to unit testing.