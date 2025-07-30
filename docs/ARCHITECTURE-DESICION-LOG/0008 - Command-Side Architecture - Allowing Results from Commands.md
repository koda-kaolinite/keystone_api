## **Title: Allowing Results from Commands**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

The pure Command Query Separation (CQS/CQRS) principle dictates that commands (write operations) should not return data, having a "void" result to ensure they do not have read-side effects.

However, in practical API development scenarios, such as creating a new resource via a `POST` request, it is a standard and efficient practice for the server to return the identifier (`ID`) of the newly created resource. Forcing the client to make a second call (a query) just to get the ID is inefficient and adds complexity.

---

### **Decision**

We have decided to adopt a **pragmatic** approach and allow commands to return a result in specific, well-defined cases.

This decision aligns our CQRS implementation with RESTful API best practices and is reflected in our module facade interface (defined in [[0006 - Applying CQRS within Modules||ADR-6]]), which already accounts for commands with and without a result.

The primary use for commands that return data will be:

1. **Resource Creation:** To return the `ID` or unique identifier of the created object.
2. **Ambiguous Operations:** For processes like authentication, which modify state (create a session) and fundamentally need to return a result (an access token).

---

### **Consequences**

- **Implementation & Complexity**
    
    - To simplify the design, we will unify the command definition into a single generic interface, `ICommand<TResult>`. Commands that don't return a value will simply implement the interface using the `Void` type, as `ICommand<Void>`.
    - This may add slight complexity to the implementation of patterns that operate on commands, such as decorators for logging or validation.
- **API & Client Experience**
    
    - The API becomes more efficient and user-friendly, as the client (our front-end) can immediately get the `ID` of a new resource without needing a second request.
- **Guiding Principle (Cautionary Use)**
    
    - This approach should be the **exception, not the rule**.
    - The vast majority of commands (especially updates and deletions) should continue to return no result (`void`), strictly adhering to the principle to avoid unnecessary complexity.