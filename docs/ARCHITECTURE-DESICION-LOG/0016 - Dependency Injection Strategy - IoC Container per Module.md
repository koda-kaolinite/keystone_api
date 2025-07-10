## **Title: Dependency Injection Strategy: IoC Container per Module**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

To process any Command or Query, a graph of dependent objects (e.g., handlers, repositories, services) must be constructed. We need to decide how the Inversion of Control (IoC) container, responsible for resolving these dependencies, will be structured within our modular architecture.

---

### **Possible Solutions**

1. **Single Global IoC Container:** A standard approach where one IoC container is configured in the main host project. This single container knows how to resolve dependencies for the entire application.
2. **IoC Container per Module:** A less common approach where each business module is responsible for its own IoC container. The main host application would only know how to access the "entry point" of each module, not its internal dependencies.

---

### **Decision**

We will implement **Solution 2: An IoC Container per Module**.

While this is a non-standard approach, it is the purest way to enforce module autonomy and loose coupling. Each module becomes a self-contained "black box" that manages its own internal dependencies. The main host application does not need to know about a module's internal repository or service implementations, which prevents architectural coupling and knowledge leaks.

This decision prioritizes long-term module independence over the initial convenience of a centralized container.

---

### **Consequences**

- **Autonomy & Encapsulation**
    
    - This is the strongest possible enforcement of module boundaries. A module can add, remove, or change its internal dependencies without impacting any other module or the host application.
    - Loose coupling is maximized, as the host application has zero compile-time dependencies on the infrastructure or domain layers of the modules.
- **Implementation & Complexity**
    
    - Some boilerplate code for setting up the IoC container will be duplicated across each module.
    - The implementation is less conventional, but still straightforward. Each module will expose a composition root where its container is configured.
    - The host application's startup logic becomes simpler, as it only needs to initialize each module's entry point, not register hundreds of individual services.
- **Developer Experience**
    
    - When working within a module, a developer only needs to reason about that module's specific container, reducing cognitive load.
    - It reinforces the "black box" nature of each module, guiding developers to respect the established boundaries.