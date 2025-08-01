## **Title:** 0024 - Synchronous Command Execution with Asynchronous Persistence

**Status:** Accepted

### Context

In our event-driven architecture, `CommandHandlers` are responsible for executing business logic and publishing domain events, while `TransactionalEventListeners` handle the persistence of aggregates based on these events. Initially, `CommandHandlers` often used `CompletableFuture.runAsync()` to execute their logic asynchronously and return a `CompletableFuture` immediately.

This approach presented two significant issues:
1.  **Transactional Inconsistency:** `CompletableFuture.runAsync()` executes its logic in a separate thread, which breaks the transactional context of the main request thread. This could lead to domain events being published outside the intended transaction, causing unpredictable behavior with `@TransactionalEventListener` (which expects events to be published within an active transaction to be processed after commit).
2.  **Client-Side Inconsistency:** The `CommandHandler` would return a `CompletableFuture` that completed as soon as the business logic finished, *before* the actual persistence operation (handled by the listener) was confirmed. This meant the client could receive a `200 OK` response with a resource ID, but if the subsequent asynchronous persistence failed, the client would have an ID for a non-existent resource, leading to data inconsistencies and poor user experience.

### Decision

To ensure client-side consistency and proper transactional behavior while maintaining our event-driven, decoupled architecture, we have adopted a "blocking for result" pattern using `CompletableFuture` as a synchronization mechanism.

The implementation details are as follows:
1.  **Event Modification:** Domain events (`PropertyCreatedEvent`, `ImageDisabledEvent`, etc.) are modified to carry a `CompletableFuture<TResult>` (e.g., `CompletableFuture<UUID>` for creation, `CompletableFuture<Void>` for updates). This future acts as a communication bridge for the operation's final result.
2.  **`CommandHandler` Refactoring:**
    *   The `handle` method of `ICommandHandler` implementations is now annotated with `@Transactional`. This ensures that the business logic, aggregate state changes, and event registration occur within a single, atomic transaction.
    *   A new, uncompleted `CompletableFuture` is created within the `CommandHandler`.
    *   This `CompletableFuture` is passed to the aggregate's methods (e.g., `propertyFactory.createHouse(details, future)`, `property.disable(future)`), which then include it in the domain event.
    *   The `CommandHandler` publishes the events and **returns this uncompleted `CompletableFuture`**. The HTTP request (via `ModuleClient` and `Controller`) remains pending, awaiting the future's completion.
3.  **`TransactionalEventListener` Role:**
    *   The `TransactionalEventListener` continues to listen for the domain event.
    *   **Crucially, after successfully performing the persistence operation (e.g., `propertyRepository.save(property)`), it completes the `CompletableFuture` carried by the event (`event.future().complete(result)`).**
    *   If any error occurs during persistence, the future is completed exceptionally (`event.future().completeExceptionally(exception)`), propagating the error back to the client.

### Consequences

#### Positive
-   **Client Consistency:** The client receives a successful response (and the resource ID) only after the persistence operation has been successfully committed to the database.
-   **Proper Error Propagation:** Persistence failures are propagated back to the client, allowing for appropriate error handling and feedback.
-   **Transactional Integrity:** All business logic and event publishing occur within a single transaction, ensuring atomicity and data consistency.
-   **Decoupling Maintained:** The `CommandHandler` remains decoupled from the persistence mechanism. It orchestrates the business logic and event publishing, while the listener handles the actual saving.
-   **Clear Flow:** The `CompletableFuture` explicitly signals the completion of the asynchronous persistence back to the synchronous request flow.

#### Negative or Trade-offs
-   **Increased Complexity in Signatures:** Domain events, aggregate methods, and `CommandHandler` signatures become more complex due to the inclusion of `CompletableFuture`.
-   **Explicit Future Management:** Developers must explicitly create, pass, and complete `CompletableFuture` instances, adding a small amount of boilerplate code.
-   **Potential for Deadlocks/Timeouts:** If the `CompletableFuture` is not completed (due to a bug or unhandled exception in the listener), the client request will hang until a timeout occurs. This necessitates robust error handling in the listener.