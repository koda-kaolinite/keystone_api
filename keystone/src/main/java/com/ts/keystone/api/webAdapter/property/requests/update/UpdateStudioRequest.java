package com.ts.keystone.api.webAdapter.property.requests.update;

public record UpdateStudioRequest(
    java.math.BigDecimal areaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit areaUnit,
    int bathroom,
    int floor,
    java.math.BigDecimal condominiumFeeAmount,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.Currency condominiumFeeCurrency,
    boolean hasBalcony,
    boolean isFurnished
) implements UpdateDetailsRequest {}

