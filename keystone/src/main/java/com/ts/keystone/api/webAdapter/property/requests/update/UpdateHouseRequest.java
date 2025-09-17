package com.ts.keystone.api.webAdapter.property.requests.update;

// Similar to CreateHouseRequest, for updating fields.
// For simplicity, fields are not Optional, assuming full object update.
public record UpdateHouseRequest(
    int bedrooms,
    int suites,
    int bathrooms,
    int parkingSpaces,
    java.math.BigDecimal totalAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit totalAreaUnit,
    java.math.BigDecimal builtAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit builtAreaUnit,
    int yearBuilt,
    String description,
    boolean hasGarage,
    boolean hasPool,
    boolean hasBalcony
) implements UpdateDetailsRequest {}
