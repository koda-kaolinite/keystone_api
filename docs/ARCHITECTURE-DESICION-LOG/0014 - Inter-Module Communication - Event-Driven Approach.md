## **Title: Inter-Module Communication: Event-Driven Approach**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Our architecture is composed of autonomous modules, but business processes will often require these modules to communicate. For example, when a `Property` is published, the `Compliance` module may need to be notified. We must define the primary communication pattern to ensure that this integration does not compromise module autonomy and loose coupling.

---

### **Possible Solutions**

1. **Direct Synchronous Calls:** One module's Application Service directly calls a method on another module's public facade. This is a simple, blocking call that provides immediate consistency.
2. **Event-Driven Asynchronous Communication:** A module publishes a `Domain Event` when something significant happens. Other modules can subscribe to these events and react accordingly, without the publishing module knowing about the subscribers. This follows the Publish/Subscribe pattern.

---

### **Decision**

We will adopt **Solution 2: Event-driven, asynchronous communication** as the primary and preferred method for inter-module integration.

This is a strategic choice to achieve the maximum level of autonomy and loose coupling between our modules. A module should not have direct, compile-time dependencies on another module. Instead, they will only be coupled to the event contracts and a central event bus/middleware.

While this is the default, direct synchronous calls may be used as a deliberate **exception**, but they must be justified and documented, typically in cases where immediate consistency is a critical, non-negotiable requirement.

---

### **Consequences**

- **Architectural Impact**
    
    - **Loose Coupling & High Autonomy:** Modules are highly independent. A new module can subscribe to existing events without requiring any changes to the publishing modules.
    - **Eventual Consistency:** As communication is asynchronous, we must accept and design for eventual consistency between modules. For example, if Module A publishes an event, the changes in subscribing Module B will not occur in the same transaction.
    - **Resilience:** The failure of a subscribing module does not cause the publishing module's transaction to fail.
- **Implementation & Complexity**
    
    - We must implement an in-process **Publish/Subscribe mechanism** (an event bus or middleware, like MediatR) to handle event dispatching.
    - The overall solution is more complex than direct calls, as it requires reasoning about asynchronous flows and potential race conditions.
- **Design & Governance**
    
    - **Published Language:** Domain Events become the public, published contract of our Bounded Contexts (modules). Their design is critical.
    - **Contract Stability:** The structure of these events should be as stable as possible, as changes can impact multiple subscribing modules.