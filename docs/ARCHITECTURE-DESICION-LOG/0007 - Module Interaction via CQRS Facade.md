## **Title: Module Interaction via CQRS Facade**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

A standardized communication contract is required between the API layer and the business modules. This contract must enforce strong encapsulation, ensuring that each module's internal complexity is hidden and that interactions are explicit and controlled. This supports the goal of maximum module autonomy.

---

### **Decision**

We will implement the **Facade** design pattern for all inter-module communication. Specifically, each module will expose a single, consistent interface that uses a Command and Query Responsibility Segregation (CQRS) approach.

The public contract for every module will be an interface with a structure similar to this:

```java
public interface IModuleClient {  
    <TResult> CompletableFuture<TResult> executeCommandAsync(ICommand<TResult> command);  
  
    <TResult> CompletableFuture<TResult> executeQueryAsync(IQuery<TResult> query);  
}
```

The API layer (and any other module) will interact with a module _exclusively_ through this facade. All internal classes, entities, and logic within the module will be hidden (e.g., marked as `internal`), enforcing strict encapsulation.

---

### **Consequences**

- **Encapsulation & Decoupling**
    
    - The internal implementation of a module can be changed freely without affecting the API layer, as long as the command/query contracts are not broken.
    - This creates a highly decoupled architecture, which is essential for long-term maintainability and the potential future extraction of modules into micro services.
- **Development & Simplicity**
    
    - API controllers become simple, lean dispatchers, forwarding requests to the appropriate module's facade without containing any business logic themselves.
    - The uniform interface simplifies how developers reason about and implement communication between different parts of the system.
    - Testability is greatly improved, as the facade interface can be easily mocked when testing API controllers.
- **Required Discipline**
    
    - Developers must be diligent in maintaining module encapsulation. All interactions _must_ go through the defined Commands and Queries.
    - This may require slightly more upfront work, as developers need to define explicit command/query objects instead of calling internal services directly.