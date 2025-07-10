## **Title: Modular Decomposition by Business Subdomain**

**Date:** 2025-07-04

**Status:** `Accepted`

---

### **Context**

Given our commitment to a **Modular Monolith architecture**, we must define the primary module boundaries for the application. Following a detailed analysis, the business requirements were organized into the following distinct subdomains: `Property Management` (Core), `Property Submissions` (Supporting), `Logs` (Generic) and `Administration` (Generic), `Notification` (Generic), `Files` (Generic).
 
A clear, coarse-grained decomposition is required to guide development and ensure module autonomy.

---

### **Decision**

We will decompose the monolith into distinct modules, where **each module directly maps to a business subdomain**. This aligns our software architecture with the structure of the business, a key principle for building maintainable and scalable systems.

The following modules will be created:

- **`PropertyManagement`:** Encapsulates the core business logic for managing, enriching, and matching published property listings.
- **`PropertySubmissions`:** Manages the entire workflow for property owners to submit new properties, including files uploads.
- **`Administration`:** Manages all generic Identity & Access Management functions, such as user logins, permissions, and roles for internal staff.
- **`Logs`:** Capture a secure and immutable record of all significant user and system actions to ensure accountability, support compliance, and enable diagnostics.
- **`Notification`:** Handles all notification delivery mechanisms including email, SMS, and push notifications across the system.
- **`Files`:** Manages file storage, retrieval, and processing operations for documents, images, and other media assets.

This structure is a deliberate investment in a clean architecture. By isolating responsibilities from the start, we prioritize the long-term maintainability and independent evolution of each business capability.

---

### **Consequences**

- **Development & Team Autonomy**
    - This creates clear ownership, with each module representing a self-contained business capability.
      
- **Architectural & Technical Implementation**
    - **Strict Boundaries:** Public APIs must be defined for each module, serving as the sole entry point for inter-module communication. Direct internal access between modules is prohibited.
    - **Data Segregation:** Each module is responsible for its own data persistence. This enforces encapsulation and prepares the system for a potential future migration to microservices.
    - **Shared Kernel:** A shared library will be required for common code (e.g., base classes, shared contracts, cross-cutting concerns) to avoid no sense duplication.
    - **Presentation Layer:** The API controller module will act as a composer, integrating functionality by calling via events the infrastructure layer of the various modules.
      
- **Complexity & Maintainability**
    - The overall solution complexity will increase initially due to the need to manage module boundaries and communication.
    - However, the complexity _within_ each module will be significantly reduced, making them easier to understand, test, and maintain.
    - This results in a clear separation of concerns and well-modeled business concepts, preventing the emergence of large, unmanageable "god classes."