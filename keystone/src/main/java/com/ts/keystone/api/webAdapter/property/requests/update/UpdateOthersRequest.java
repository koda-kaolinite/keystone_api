package com.ts.keystone.api.webAdapter.property.requests.update;

public record UpdateOthersRequest(
    String typeDescription,
    java.math.BigDecimal areaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit areaUnit,
    int yearBuilt,
    String legalStatus,
    String description
) implements UpdateDetailsRequest {}
