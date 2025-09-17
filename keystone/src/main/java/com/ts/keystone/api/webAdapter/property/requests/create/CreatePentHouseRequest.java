package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePentHouseRequest implements CreateDetailsRequest {
    private int bedrooms;
    private int suites;
    private int bathrooms;
    private int parkingSpaces;
    private BigDecimal totalAreaValue;
    private AreaUnit totalAreaUnit;
    private BigDecimal builtAreaValue;
    private AreaUnit builtAreaUnit;
    private BigDecimal terraceAreaValue;
    private AreaUnit terraceAreaUnit;
    private String viewDescription;
    private boolean hasGarage;
    private boolean hasPool;
    private boolean hasPrivateElevator;
}
