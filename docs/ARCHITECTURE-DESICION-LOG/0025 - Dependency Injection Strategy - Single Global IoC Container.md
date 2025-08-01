## **Title:** 0025 - Dependency Injection Strategy: Single Global IoC Container

**Status:** Accepted

**Replaces:** [ADR-0016](0016%20-%20Dependency%20Injection%20Strategy%20-%20IoC%20Container%20per%20Module.md)

### Context

To process any Command or Query, a graph of dependent objects (e.g., handlers, repositories, services) must be constructed. We need to define how the Inversion of Control (IoC) container, responsible for resolving these dependencies, will be structured within our modular architecture.

ADR-0016 previously proposed a strategy of one IoC container per module to enforce strict encapsulation. However, we decided the actual implementation has naturally followed the standard Spring Framework pattern, which utilizes a single, application-wide `ApplicationContext`. This approach is more idiomatic within the Spring ecosystem and simpler to manage.

### Decision

We will officially adopt a **Single Global IoC Container** strategy, managed by the Spring Framework's `ApplicationContext`.

This single container will be responsible for discovering, configuring, and wiring all beans (components, services, repositories, etc.) from all modules within the application. Mechanisms like the `CommandBus` and `DetailsMapperRegistry` will leverage this central `ApplicationContext` to discover their respective handlers and mappers at runtime using `applicationContext.getBeansOfType(...)`.

This decision prioritizes simplicity, developer productivity, and alignment with the conventional practices of the Spring Framework over the stricter, but more complex, isolation proposed in the superseded ADR.

### Consequences

-   **Simplicity and Idiomatic Implementation:** The project follows the standard, well-documented approach for dependency injection in Spring, reducing complexity and making it easier for new developers to understand.
-   **Centralized Configuration:** All dependency injection configuration is managed by Spring's auto-configuration and component scanning, simplifying the setup.
-   **Potential for Weaker Encapsulation:** This approach does not programmatically prevent a developer from injecting a bean from one module directly into another, bypassing the intended communication patterns (e.g., events or facades).
-   **Reliance on Architectural Governance:** To maintain module boundaries, we must rely on code reviews and automated architecture tests (as defined in ADR-0017) to enforce the rule that modules should not have direct dependencies on each other's internal components.