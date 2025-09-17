package com.ts.keystone.api.webAdapter.property.requests.update;

public record UpdateWareHouseRequest(
    java.math.BigDecimal storageAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit storageAreaUnit,
    java.math.BigDecimal officeAreaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit officeAreaUnit,
    java.math.BigDecimal ceilingHeightValue,
    int loadingDocks,
    String floorType,
    boolean hasLoadingRamp
) implements UpdateDetailsRequest {}

