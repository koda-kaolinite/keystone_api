package com.ts.keystone.api.webAdapter.property.requests.update;

public record UpdatePlotRequest(
    java.math.BigDecimal areaValue,
    com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit areaUnit,
    String dimensions,
    String topographyDescription,
    String zoning,
    boolean hasFence
) implements UpdateDetailsRequest {}

