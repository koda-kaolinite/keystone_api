## **Title: Google Cloud Platform Strategy**

**Date:** 2025-07-29

**Status:** `Accepted`

---

### **Context**

As our project evolves, the need for scalable, reliable, and cost-effective cloud infrastructure becomes paramount. We require solutions for both relational database management and efficient file storage (including images). Given our existing familiarity and the comprehensive suite of services offered by Google Cloud Platform (GCP), a strategic decision is needed to consolidate our cloud infrastructure choices.

---

### **Decision**

We will standardize our cloud infrastructure on **Google Cloud Platform (GCP)**, specifically utilizing **Cloud SQL for PostgreSQL** for our relational database and **Google Cloud Storage (GCS)** for file and image storage.

This decision is based on the following key advantages:

-   **Unified Cloud Ecosystem:** Leveraging a single cloud provider (GCP) simplifies management, reduces operational overhead, and streamlines integration between services. This includes consistent authentication (IAM), networking, and monitoring.
-   **Scalability and Reliability:** Both Cloud SQL and GCS offer high scalability, automatic replication, and robust backup/recovery mechanisms, ensuring high availability and data durability for our critical application data and files.
-   **Performance:** Cloud SQL provides managed PostgreSQL instances optimized for performance, while GCS offers low-latency global access to stored objects, crucial for media-rich applications.
-   **Cost-Effectiveness:** GCP's pricing models for Cloud SQL and GCS allow for flexible scaling and cost optimization based on usage patterns and storage classes.
-   **Security:** Both services provide enterprise-grade security features, including encryption at rest and in transit, network isolation, and fine-grained access controls.

---

### **Consequences**

-   **Architectural Impact:**
    -   All relational data persistence will be configured to connect to Cloud SQL for PostgreSQL instances.
    -   All file and image storage operations will be directed to Google Cloud Storage buckets.
    -   The `Files` module (as defined in ADR 0004) will specifically implement integrations with GCS.
    -   The `PropertyManagement` module (and others requiring persistence) will utilize Spring Data JPA to interact with Cloud SQL.

-   **Implementation & Complexity:**
    -   Spring Cloud GCP starters (`spring-cloud-gcp-starter-sql-postgresql` and `spring-cloud-gcp-starter-storage`) will be used to simplify integration with these services.
    -   Database migrations (e.g., Flyway or Liquibase) will need to be managed for Cloud SQL instances.
    -   Proper configuration of service accounts, IAM roles, and network access for GCP resources will be essential.

-   **Team & Maintainability:**
    -   The development team will focus on building expertise within the GCP ecosystem.
    -   Operational procedures for managing Cloud SQL instances and GCS buckets (e.g., monitoring, backups, access control) will be established.
    -   Future cloud infrastructure decisions will prioritize compatibility and integration within GCP.