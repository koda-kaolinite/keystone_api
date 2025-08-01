## Title: Domain Event Publishing and CommandHandler Responsibility Separation

**Date:** 2025-07-29

**Status:** `Accepted`

---

### **Context**

In a Clean Architecture and Domain-Driven Design (DDD) context, `CommandHandlers` are responsible for orchestrating business logic by interacting with `AggregateRoots`. `AggregateRoots` encapsulate business rules and state changes, often publishing `Domain Events` to signal that something significant has occurred within the domain.

The initial implementation of `CommandHandlers` in the `propertyManager` module, specifically `DisableImageHandler`, `DisablePropertyHandler`, `EnableImageHandler`, and `EnablePropertyHandler`, was found to be directly calling `repository.save()` after modifying the `AggregateRoot` (Property). This approach violates the principle of separation of concerns, as persistence operations should ideally be handled by the `infrastructure` layer, decoupled from the `application` layer's `CommandHandlers`.

The project's architecture already defines an event-driven communication mechanism between modules and within modules for persistence, utilizing Spring's `ApplicationEventPublisher` and `TransactionalEventListener` (e.g., `PropertyManagerPersistenceListener`). This mechanism is designed to react to `Domain Events` and handle persistence as a side effect of domain operations.

Therefore, there is a need to align the `CommandHandler` behavior with the established architectural principles, ensuring that `Domain Events` are properly published and that persistence is managed by the dedicated `infrastructure` listeners.

---

### **Decision**

`CommandHandlers` will be responsible for:
1. Retrieving the `AggregateRoot` from the repository.
2. Invoking the appropriate business method on the `AggregateRoot` to perform the desired action (which may register `Domain Events` internally).
3. Publishing all `Domain Events` accumulated by the `AggregateRoot` using Spring's `ApplicationEventPublisher`.
4. Clearing the `Domain Events` from the `AggregateRoot` after publication.

`CommandHandlers` **will no longer directly call `repository.save()`** after modifying the `AggregateRoot`. Instead, the persistence of the `AggregateRoot` will be handled by `TransactionalEventListeners` (e.g., `PropertyManagerPersistenceListener`) in the `infrastructure` layer, which will subscribe to the published `Domain Events` and perform the necessary `repository.save()` operations.

This decision reinforces the Inversion of Control (IoC) principle and the Clean Architecture's dependency rule, where the `application` layer (CommandHandlers) depends on abstractions (interfaces for repositories and event publishers), and the `infrastructure` layer provides the concrete implementations and handles side effects like persistence.

---

### **Consequences**

*   **Architectural Impact:**
    *   Strengthens the adherence to Clean Architecture and DDD principles by clearly separating concerns between the `application` and `infrastructure` layers.
    *   Improves the testability of `CommandHandlers` as they become less coupled to persistence mechanisms.
    *   Reinforces the event-driven nature of the system, making the flow of data and state changes more explicit through `Domain Events`.

*   **Implementation & Complexity:**
    *   Requires modification of existing `CommandHandlers` to inject `ApplicationEventPublisher` and to publish/clear `Domain Events`.
    *   Ensures that `TransactionalEventListeners` are correctly implemented and subscribed to handle the persistence of `AggregateRoots` based on `Domain Events`.
    *   The overall system becomes more robust and maintainable due to clearer responsibilities.

*   **Team & Maintainability:**
    *   Promotes a deeper understanding of DDD and Clean Architecture principles within the development team.
    *   Reduces the likelihood of accidental direct persistence calls from the `application` layer.
    *   Simplifies debugging of persistence issues by centralizing the save operations within event listeners.