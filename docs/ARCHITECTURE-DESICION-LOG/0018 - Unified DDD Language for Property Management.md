## **Title: Unified DDD Language for Property Management**

**Date:** 2025-07-04

**Status:** `Accepted` 
### **Context**

 Our Property Management platform involves multiple stakeholders (property owners, buyers, renters, administrators) and complex business processes. Without a shared vocabulary, communication between business experts and the development team becomes prone to misunderstandings. Different team members use different terms for the same concepts, leading to confusion in requirements, code inconsistencies, and potential bugs. The current codebase contains mixed terminology where the same business concept might be represented by different class names, properties, or database fields across different modules.

### **Decision**

We will establish and enforce a **Unified Language** throughout the entire Property Management platform. This ubiquitous language will be consistently used across all artifacts: code, documentation, user interfaces, APIs, database schemas, and conversations.

**Core Domain Terms:**

- **Platform**: Our application
- **Property**: Real estate asset available for sale or rent
- **Potential Buyer/Renter**: Person interested in buying or renting properties
- **Potential Seller** : Person interested in selling or rent a property.
- **Request for approval of property for sale/rental**: Formal request from Potential Seller to the platform about a property
- **Platform Administrator**: Employee who manages the platform and users
- **Property Broker**: Employee who manages the properties life cycle 

**Implementation Rules:**

1. All class names, method names, and variables must use these exact terms
2. Database tables and columns must reflect this vocabulary
3. API endpoints and response models must use consistent terminology
4. User interface labels and messages must align with domain language
5. Documentation and comments must use only approved domain terms

---

### **Consequences**

- **Communication & Clarity**
    
    - Eliminates ambiguity between business stakeholders and development team
    - Reduces onboarding time for new team members who can quickly understand the domain
    - Business rules and requirements become clearer and less prone to misinterpretation
- **Code Quality & Maintenance**
    
    - The codebase becomes more readable and self-documenting
    - Reduces cognitive load when working across different modules
    - Easier to trace business concepts from requirements through to implementation
	
- **Validation & Governance**
    
    - Code review process must include verification of language consistency
    - Glossary document must be maintained and kept up-to-date
    - New domain concepts must go through approval process before being added to the vocabulary