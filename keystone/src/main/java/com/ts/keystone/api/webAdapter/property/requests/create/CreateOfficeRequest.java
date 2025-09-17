package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateOfficeRequest implements CreateDetailsRequest {
    private BigDecimal areaValue;
    private AreaUnit areaUnit;
    private int numberOfRooms;
    private int bathrooms;
    private int parkingSpaces;
    private int floor;
    private String buildingName;
    private boolean hasReception;
    private boolean hasAirConditioning;
}
