## **Title:** 0023 - Modeling Property Type Variations using a Composition-based "Details" Pattern

**Status:** Accepted

### Context

The `Property` aggregate needs to represent various distinct types, such as `House`, `Apartment`, `Warehouse`, and `Plot`. Each of these types has a unique set of attributes and associated business rules. For example, an `Apartment` has a `condominiumFee`, while a `Warehouse` has a `dockHeight`.

We needed to choose a modeling strategy that would accommodate these variations while adhering to our established architectural principles of Domain-Driven Design (DDD) and Clean Architecture.

### Alternatives Considered

1.  **Single Entity with a Type Enum and Nullable Fields:**
    *   **Description:** This approach uses a single `Property` entity containing all possible fields for all types. A `PropertyType` enum would distinguish the type, and fields not relevant to a specific type would be left `NULL`.
    *   **Reason for Rejection:** While simple to implement initially, this pattern leads to a bloated and confusing database schema. It violates the Rich Domain Model principle by forcing business logic (`if/switch` statements) outside the entity and breaks the Open/Closed Principle, as adding new types requires modifying the central `Property` class and its database table.

2.  **JPA Inheritance (`TABLE_PER_CLASS` or `JOINED` Strategy):**
    *   **Description:** This approach uses a class hierarchy (e.g., `Apartment extends Property`). Each subclass holds its specific fields and logic. JPA would manage mapping this hierarchy to the database.
    *   **Reason for Rejection:** While a clean object-oriented solution, it introduces significant complexity when combined with DDD Aggregates. It can be difficult to define the aggregate boundary, and polymorphic queries across different types can be inefficient. It also creates a rigid "is-a" relationship, making it difficult to change a property's type later.

### Decision

We have decided to implement the **Composition over Inheritance** principle using a "Details" pattern. This approach cleanly separates shared data from type-specific data.

The implementation is structured as follows:
1.  **Pure Domain Model:**
    *   The `Property` class in the `domain` layer is a pure POJO. It contains only the attributes common to all property types (e.g., `id`, `address`, `price`).
    *   It holds a `PropertyType` enum to identify its specific type.
    *   It contains a generic `Object details` field. This field will be populated at runtime with a specific, type-safe POJO (e.g., `HouseDetails`, `ApartmentDetails`) that holds the attributes for that property type.

2.  **JPA Entities in Infrastructure:**
    *   The responsibility for persistence is entirely within the `infrastructure` layer.
    *   The `PropertyJpaEntity` is the main JPA entity. It contains the shared fields and a `@OneToOne` relationship for *each* possible details type (e.g., `HouseDetailsJpaEntity`, `ApartmentDetailsJpaEntity`).
    *   Each `...DetailsJpaEntity` is a separate entity with its own table, containing only the fields relevant to that specific property type.

3.  **Lifecycle Management:**
    *   The `@OneToOne` relationships on `PropertyJpaEntity` are configured with `cascade = CascadeType.ALL` and `orphanRemoval = true`.
    *   This ensures that the lifecycle of a `...DetailsJpaEntity` is completely managed by its parent `PropertyJpaEntity`. There is no need for separate repositories for the details entities.

4.  **Centralized Mapping:**
    *   The `PropertyRepositoryImpl` acts as the Mapper. It encapsulates the logic for translating between the pure `Property` domain aggregate (with its specific `details` object) and the `PropertyJpaEntity` (with its multiple, mostly-null `@OneToOne` fields).

### Consequences

#### Positive
- **Clean Domain Model:** The domain layer remains completely ignorant of persistence details, upholding the principles of Clean Architecture. The `Property` aggregate is a clean, cohesive representation of the business concept.
- **High Flexibility and Scalability:** Adding a new property type is a low-impact operation. It requires creating a new `Details` POJO and a corresponding `DetailsJpaEntity`, then updating the `PropertyRepositoryImpl` mapper. The core `Property` domain model is not modified.
- **Normalized Database Schema:** The database schema is clean and normalized. Each property type has its specific attributes in a dedicated table, avoiding nullable columns for irrelevant data.
- **Strong Cohesion:** Data and logic related to a specific property type are grouped within the corresponding `Details` objects, improving code organization and maintainability.
- **DDD-Friendly:** This pattern perfectly represents the DDD Aggregate concept. The `Property` is the Aggregate Root, and the `Details` are internal components whose lifecycle is controlled by the root.

#### Negative or Trade-offs
- **Mapping Complexity:** The logic for mapping between the domain and persistence layers is concentrated in the `PropertyRepositoryImpl`. While this is a good separation of concerns, this mapper class will grow as new types are added.
- **Boilerplate Code:** Creating a new property type involves creating two `Details` classes (one domain POJO, one JPA entity) and updating the repository mapper, which introduces some boilerplate. This is a reasonable trade-off for the flexibility gained.