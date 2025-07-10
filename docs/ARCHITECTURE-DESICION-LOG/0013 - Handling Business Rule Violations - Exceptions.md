## **Title: Handling Business Rule Violations: Exceptions**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Our Aggregates are responsible for protecting their own business invariants (as per [[0012 - Domain Implementation with DDD Tactical Patterns||ADR #12]]). When a requested operation would violate one of these invariants, the Aggregate must immediately stop the process and clearly signal an error to the calling layer (the Application Service). We need to define a consistent, system-wide strategy for handling these validation failures.

---

### **Possible Solutions**

1. **Use Exceptions:** The Aggregate method throws a specific `BusinessRuleValidationException` when an invariant is broken. This immediately halts the execution flow and transfers control to a dedicated exception handler.
2. **Return a Result Object:** The Aggregate method returns a `Result` object which encapsulates either a successful outcome or an error description. The calling code is responsible for inspecting this object to determine if the operation succeeded.

---

### **Decision**

We will use **Solution 1: Throwing exceptions**.

This is a deliberate choice to adopt a **"fail-fast"** strategy. When a core business rule is violated, it represents an exceptional event that should not be handled with normal control flow (like `if/else` statements).

This approach is idiomatic in Java and simplifies the client code within the Application Layer, as it doesn't need to check the return value of every domain operation. The performance impact of throwing exceptions is considered negligible for these scenarios, as violating a core business rule is not a common or expected event. A global exception handler in the API layer can then catch these specific business exceptions and translate them into appropriate HTTP error responses.

---

### **Consequences**

- **Implementation & Standardization**
    
    - A custom base exception, `BusinessRuleValidationException`, will be created to distinguish these specific failures from other system or infrastructure exceptions.
    - Specific, descriptive exceptions will be created for different business rules (e.g., `PropertyCannotBePublishedException`) to improve clarity and debugging.
    - A global exception-handling mechanism (e.g., middleware) will be implemented in the API layer to catch these exceptions and map them to standard HTTP status codes (like `400 Bad Request` or `409 Conflict`).
- **Code Style & Readability**
    
    - The code within the Application and Domain layers becomes cleaner and more expressive, without being cluttered by `if/else` statements checking for success or failure after every method call.
- **Runtime & Monitoring Considerations**
    
    - There will be a small, but acceptable, performance cost associated with throwing exceptions.
    - We must be mindful of monitoring tools that automatically log all thrown exceptions. We may need to configure these tools to filter or handle our custom business exceptions appropriately to avoid unnecessary noise in the error logs.