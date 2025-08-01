## **Title:** 0022 - Adopt ULID as the Standard for Primary Identifiers

**Status:** Accepted

### Context

The system requires unique identifiers for domain entities such as `Property` and `Image`. The initial approach used `java.util.UUID` (version 4, random) as the primary key in the PostgreSQL database tables.

While UUID v4 guarantees global uniqueness, its completely random nature introduces a significant performance issue in databases that use B-Tree-based indexes (like PostgreSQL). New keys are inserted at random locations within the index, causing **continuous fragmentation of data pages**. As the table grows, this degrades write performance (`INSERT` operations) and can impact the efficiency of reads.

Furthermore, ordering records by their creation date requires an additional `created_at` column, as UUIDs v4 have no inherent chronological order.

### Decision

We have decided to adopt **ULID (Universally Unique Lexicographically Sortable Identifier)** as the new standard for generating all primary identifiers in the system.

The implementation will be carried out as follows:
1.  We will add the `com.github.f4b6a3:ulid-creator` library to the project for generating ULIDs.
2.  To maintain compatibility with existing code and the native PostgreSQL data type, ULIDs will be generated and immediately converted to the `java.util.UUID` type using the `UlidCreator.getMonotonicUlid().toUuid()` method.
3.  The column type in the database will remain `UUID`. This approach allows us to gain the performance benefits of ULID without requiring schema changes or a massive refactoring of the application, which already operates on the `UUID` type.

### Consequences

#### Positive
- **Improved Write Performance:** The monotonic nature of ULIDs (which start with a timestamp) ensures that new records are always appended to the end of the primary key index. This eliminates index fragmentation, resulting in significantly faster and more consistent insertions.
- **Natural Chronological Ordering:** Records can be ordered directly by their primary key to obtain a chronological creation sequence, potentially eliminating the need for a dedicated `created_at` column.
- **Minimal Impact on Existing Code:** Retaining the `java.util.UUID` type in the application and database avoids a large-scale refactoring. The change is contained within the creation points of new entities.
- **Guaranteed Uniqueness:** ULID maintains the guarantee of global uniqueness, making it safe for use in distributed environments.

#### Negative or Trade-offs
- **Loss of Textual Representation:** The canonical string representation of a ULID (a 26-character string, e.g., `01H8XGJWBWBAQ4J1Z5B5DRK4XG`) is lost. When displayed, the identifier will have the format of a standard `UUID`. This is an acceptable trade-off for the performance gains.
- **Abstraction in Code:** A developer seeing a `UUID` type in the code needs to be aware (through this documentation) that it was generated from a ULID. Extracting the timestamp from the ID requires an explicit conversion (`Ulid.from(uuid)`), adding a small extra step when this information is needed.
- **New Dependency:** The project now has a new external dependency (`com.github.f4b6a3:ulid-creator`).