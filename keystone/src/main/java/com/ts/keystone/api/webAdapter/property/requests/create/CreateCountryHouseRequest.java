package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateCountryHouseRequest implements CreateDetailsRequest {
    private int bedrooms;
    private int suites;
    private int bathrooms;
    private BigDecimal totalAreaValue;
    private AreaUnit totalAreaUnit;
    private BigDecimal builtAreaValue;
    private AreaUnit builtAreaUnit;
    private String description;
    private boolean hasGarage;
    private boolean hasPool;
    private boolean hasRiverOrLake;
}
