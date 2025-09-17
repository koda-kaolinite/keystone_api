### **ADR-0027: Migrating to Spring's Native Event System for Command/Query/Integration Event Dispatching**

**Status:** `Accepted`

#### **Context**

The project's architecture previously utilized custom in-memory event buses (`CommandBus`, `QueryBus`, `IntegrationEventsBus`) for dispatching commands, queries, and integration events to their respective handlers. These custom buses were designed to manage the routing of generic `ICommand`, `IQuery`, and `IIntegrationEvent` types to their corresponding `ICommandHandler`, `IQueryHandler`, and `IIntegratedEventHandler` implementations.

A recurring and persistent issue encountered was the `IllegalStateException: No handler for class...` error. This stemmed from the inherent complexity of reliably resolving generic types of Spring-managed beans, especially when these beans were proxied by Spring AOP (e.g., due to `@Transactional` annotations). Despite multiple attempts to implement robust generic type resolution using `GenericTypeResolver` and `AopUtils.getTargetClass()`, the custom bus implementations remained brittle and prone to failure, introducing unnecessary complexity and fragility into the core dispatching mechanism. This forced developers to spend time debugging infrastructure concerns rather than focusing on business logic.

#### **Decision**

We will migrate from our custom in-memory event buses (`CommandBus`, `QueryBus`, `IntegrationEventsBus`) to Spring Framework's native event publishing and listening mechanism, leveraging `ApplicationEventPublisher` and `@EventListener`.

This decision involves the following key changes:

1.  **Removal of Custom Buses:** The `CommandBus`, `QueryBus`, and `IntegrationEventsBus` classes will be removed from the codebase.
2.  **`IModuleClient` Refactoring:** The `IModuleClient` implementation will be simplified to directly inject and utilize Spring's `ApplicationEventPublisher`. Its `executeCommandAsync`, `executeQueryAsync`, and `executeIntegrationEventAsync` methods will now publish the respective command, query, or integration event instances as Spring `ApplicationEvent`s.
3.  **Event/Command/Query Base Classes:**
    *   `BaseCommand`, `BaseQuery`, and `BaseIntegrationEvent` will be refactored to extend `org.springframework.context.ApplicationEvent`.
    *   They will continue to carry a `CompletableFuture` instance, allowing for asynchronous result propagation back to the caller.
4.  **Handler Refactoring:**
    *   The custom handler interfaces (`ICommandHandler`, `IQueryHandler`, `IIntegratedEventHandler`) will be removed.
    *   Concrete handler implementations (e.g., `CreatePropertyCommandHandler`, `PageableSearchQueryHandler`) will be refactored into standard Spring `@Component` beans.
    *   Their `handle` methods will be annotated with `@EventListener` (or `@TransactionalEventListener` for handlers requiring transactional context), directly receiving the command, query, or integration event as a method parameter. These methods will be responsible for completing the `CompletableFuture` carried by the event.
5.  **Domain Event Publishing:** The `AggregateRoot`'s mechanism for publishing domain events will continue to use `ApplicationEventPublisher`, ensuring consistency with the new dispatching strategy. `IDomainEvent` will continue to carry a `CompletableFuture` for transactional consistency.

#### **Consequences**

*   **Positive:**
    *   **Simplified Event Dispatching:** Eliminates complex custom logic for generic type resolution and event routing, leveraging Spring's robust and battle-tested event infrastructure.
    *   **Increased Robustness and Stability:** Spring's event system inherently handles AOP proxying and bean lifecycle management, significantly reducing the occurrence of "No handler for class..." errors and making the dispatching mechanism more reliable.
    *   **Idiomatic Spring Development:** Aligns the project more closely with standard and widely adopted Spring practices, improving code readability, maintainability, and reducing the learning curve for new team members.
    *   **Reduced Maintenance Overhead:** Less custom infrastructure code to maintain and debug.
    *   **Clearer Separation of Concerns:** Reinforces the distinction between the message (command/query/event) and the listener (handler), as Spring's `@EventListener` clearly delineates the event consumption.
*   **Negative / Trade-offs:**
    *   **Refactoring Effort:** Requires a one-time refactoring effort for all existing command, query, and integration event classes, as well as their respective handlers.
    *   **Implicit Dispatching:** While powerful, the `@EventListener` mechanism is more implicit than explicit bus dispatching. Developers need to be aware of Spring's event processing lifecycle.
