## **Title: API Layer Strategy: Single Host Application**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

The system requires a public REST API to serve external clients, with the primary initial client being our front-end application. We must decide how this API layer will be structured in relation to our business modules within the Modular Monolith.

---

### **Possible Solutions**

1. **Single API Host Project:** Create one main web application project that serves as the single API entry point. This project would directly reference all business modules (`PropertyManagement`, `PropertySubmissions`, etc.), and its controllers would orchestrate calls to the appropriate module.
    
2. **Multiple API Projects per Module:** Create a dedicated API project for each business module (e.g., `PropertyManagement.Api`, `PropertySubmissions.Api`). A main host application would then act as a lightweight gateway or compose these separate APIs.
    

---

### **Decision**

We will implement **Solution 1: A single API Host Project**.

Creating a separate API project for each module introduces an unnecessary layer of complexity and project overhead with little practical value at this stage. A logical organization of endpoints within the single host project (e.g., using feature folders or clear naming conventions for controllers) is sufficient to maintain clarity and separation of concerns. This approach prioritizes simplicity, a faster development cycle, and easier deployment.

---

### **Consequences**

- **Simplicity & Maintenance**
    
    - We will have only one API project to configure, deploy, and maintain.
    - API configuration (routing, authentication, CORS, etc.) is centralized and simplified.
    - The overall architectural complexity is lower.
- **Development & Performance**
    
    - Each API controller is responsible for dispatching commands or queries to the correct business module.
    - Build times will be shorter due to fewer projects in the solution.
    - We avoid any potential network latency that might arise from calls between separate API projects in the future.
- **Potential Trade-offs**
    
    - The single API host project will have compile-time dependencies on all business modules.
    - Controllers may have broader responsibilities, though this can be mitigated with patterns like MediatR to keep them lean.