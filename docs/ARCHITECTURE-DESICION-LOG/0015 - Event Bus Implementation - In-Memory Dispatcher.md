## **Title: Event Bus Implementation: In-Memory Dispatcher**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Following our decision to use an event-driven architecture for inter-module communication ([[0014 - Inter-Module Communication - Event-Driven Approach||ADR 14]]), we must now select a concrete implementation for the "event bus". As we are operating within a single-process Modular Monolith, we need a mechanism to dispatch events from a publisher to one or more subscribers within the same application process.

---

### **Possible Solutions**

1. **In-Memory Events Bus:** A simple Publish/Subscribe implementation that runs entirely within the application's memory. This could be a custom implementation or a lightweight library (like MediatR).
2. **External Message Broker:** Integrating a full-fledged external messaging system like RabbitMQ, Azure Service Bus, or Kafka. This involves network communication and adds an external infrastructure dependency.

---

### **Decision**

We will implement **Solution 1: An In-Memory Events Bus**.

At this stage, our integration needs are confined to simple publish/subscribe scenarios within a single process. Adopting an external message broker would introduce significant operational and developmental complexity (networking, configuration, learning curve) for features we do not currently require.

We will start with the simplest possible solution that meets our needs, following the YAGNI (You Ain't Gonna Need It) principle. The loosely coupled nature of our event-based architecture ensures that we can evolve and switch to an external broker in the future if the need arises (e.g., if a module is extracted into a separate service).

---

### **Consequences**

- **Implementation & Dependencies**
    
    - We will implement or adopt a library for an in-memory Publish/Subscribe pattern.
    - All modules will have a dependency on the chosen event bus abstraction to publish events or register handlers.
- **Simplicity & Performance**
    
    - The solution is simple to implement and requires no additional infrastructure.
    - Communication is fast and efficient as it occurs entirely in-memory, with no network overhead.
- **Future Scalability (The Main Trade-off)**
    
    - This solution is strictly for a **single-process monolith**. It does not support communication across different processes or services.
    - If we decide to extract a module into a separate microservice in the future, it will be a major architectural driver that **requires** replacing the in-memory bus with an external message broker for communication with that new service.