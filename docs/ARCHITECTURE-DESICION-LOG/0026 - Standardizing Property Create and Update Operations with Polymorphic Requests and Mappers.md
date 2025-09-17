## **Title:** Standardizing Property Create and Update Operations with Polymorphic Requests and Mappers**

**Status:** `Accepted`

### **Context**

The initial approach for creating and updating properties required a dedicated Controller endpoint, a `Command` object, and a `CommandHandler` for each distinct `PropertyType` (e.g., `CreateHouseCommand`, `CreateApartmentCommand`). This pattern was not scalable, leading to significant code duplication and increasing the maintenance burden as new property types were introduced. A unified, scalable mechanism was needed to handle these operations for all property variations through a single, consistent interface.

### **Decision**

We will adopt a polymorphic pattern for create and update operations, centralizing the logic into a single set of `Commands`, `CommandHandlers`, and `Controller` endpoints.

The implementation is structured as follows:

1.  **Unified Controller Endpoints:** A single `POST /api/property` endpoint for creation and a `PATCH /api/property/{id}` endpoint for updates will serve all property types.

2.  **Generic Commands:** Generic `CreatePropertyCommand` and `UpdatePropertyCommand` will be used. These commands will carry polymorphic request DTOs.

3.  **Polymorphic Request DTOs:**
    *   A generic `CreatePropertyRequest` will contain common property attributes, a `PropertyType` enum, and a polymorphic `details` field of type `CreateDetailsRequest`. The client will populate this `details` field with a specific object corresponding to the `PropertyType` (e.g., `CreateHouseRequest`, `CreateWarehouseRequest`).
    *   A similar structure will be used for `UpdatePropertyRequest` with an `UpdateDetailsRequest` field.

4.  **Mapper Registries:**
    *   We will introduce `CreateDetailsMapperRegistry` and `UpdateDetailsMapperRegistry`. These components act as factories that discover all `ICreateDetailsMapper` and `IUpdateDetailsMapper` beans from the Spring `ApplicationContext` at startup.
    *   Each mapper is responsible for converting a specific request DTO (e.g., `CreateHouseRequest`) into its corresponding domain `Details` object (e.g., `HouseDetails`).

5.  **Centralized Command Handlers:**
    *   The `CreatePropertyCommandHandler` will receive the `CreatePropertyCommand`. It will use the `CreateDetailsMapperRegistry` to find the appropriate mapper based on the `PropertyType` in the request. It then invokes this mapper to get the domain `Details` object and proceeds with creating the `Property` aggregate.
    *   The `UpdatePropertyCommandHandler` will follow a similar logic for updates.

This pattern decouples the core command handling logic from the specifics of each property type, allowing the system to be extended with new types without modifying the primary workflow.

### **Consequences**

*   **Positive:**
    *   **Scalability:** Adding a new property type becomes a low-impact operation, requiring only the creation of a new `Details` request DTO and a corresponding `DetailsMapper` implementation. No changes are needed in the `Controller` or `CommandHandlers`.
    *   **Reduced Boilerplate:** Eliminates the need for repetitive Controller endpoints and Command/CommandHandler classes for each property type, significantly simplifying the codebase.
    *   **Simplified API Surface:** Provides a single, consistent point of entry for clients to create and update any type of property, improving API usability.
    *   **Adherence to OCP (Open/Closed Principle):** The core create/update mechanism is closed for modification but open for extension with new property types.

*   **Negative / Trade-offs:**
    *   **Increased Abstraction:** The use of registries and polymorphic DTOs introduces a higher level of abstraction that may require a slight learning curve.
    *   **Runtime Discovery:** The mapping logic relies on the runtime discovery of mappers via the Spring `ApplicationContext`. While powerful, this can be less transparent than direct compile-time wiring.
    *   **API Contract Complexity:** The API contract for the generic `CreatePropertyRequest` and `UpdatePropertyRequest` is more complex, as it involves a nested, polymorphic `details` object that clients must construct correctly.