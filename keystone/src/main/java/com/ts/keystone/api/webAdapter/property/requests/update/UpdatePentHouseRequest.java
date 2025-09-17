package com.ts.keystone.api.webAdapter.property.requests.update;

public record UpdatePentHouseRequest(
    int bedrooms,
    int suites,
    int bathrooms,
    int parkingSpaces,
    java.math.BigDecimal totalAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit totalAreaUnit,
    java.math.BigDecimal builtAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit builtAreaUnit,
    java.math.BigDecimal terraceAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit terraceAreaUnit,
    String viewDescription,
    boolean hasGarage,
    boolean hasPool,
    boolean hasPrivateElevator
) implements UpdateDetailsRequest {}

