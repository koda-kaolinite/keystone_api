## 0019. Definition of Basic Dependencies

**Date:** 2025-07-09

**Status:** `Accepted`

---

### **Context**

Based on the requirements gathering and planning completed to date, the project necessitates the creation of a new application. A key aspect of this phase involves deciding on the implementation strategy for each stage, specifically identifying which dependencies will be leveraged from external sources and which will be developed internally.

---

### **Decision**

The following dependencies will be utilized for this project:

- **Lombok:** To reduce boilerplate code and enhance developer productivity.
    
- **Spring Boot GraalVM Native Support:** To compile Spring applications into native executables using the GraalVM Native Image compiler, improving startup time, easy deployment and reducing memory footprint.
    
- **Spring Modulith:** To facilitate modular design and enable architectural testing between modules, ensuring a well-structured and maintainable codebase.
    
- **Spring Boot DevTools:** To provide live reload capabilities and configurations that promote faster and more productive development cycles.
    
- **Spring Web:** For implementing controllers using the Model-View-Controller (MVC) pattern.
    
- **Spring Security:** To manage authentication and access control mechanisms, ensuring secure application access.
    
- **Spring Data JPA:** To simplify data modeling and database operations, abstracting away much of the boilerplate code associated with data persistence.
    
- **PostgreSQL Driver:** For establishing connections to the relational database.
    
- **Spring Validation:** To accelerate code production through practical and concise validation annotations.
    
- **Java Mail Sender:** For sending emails via SMTP servers.
    
- **Google Cloud Support:** To streamline cloud configuration within the Google Cloud Platform (GCP).
    
- **Google Cloud Storage:** For storing files and images in the cloud.
    
- **Cloud SQL:** For connecting to the database hosted on the GCP platform.
    

---

### **Consequences**

These dependencies will be integrated into the project at its inception. Any future additions or modifications to this set of dependencies will undergo a formal review and approval/rejection process.

---