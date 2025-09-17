package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateHouseRequest implements CreateDetailsRequest {

    // HouseDetails fields
    private int bedrooms;
    private int suites;
    private int bathrooms;
    private int parkingSpaces;
    private BigDecimal totalAreaValue;
    private AreaUnit totalAreaUnit;
    private BigDecimal builtAreaValue;
    private AreaUnit builtAreaUnit;
    private int yearBuilt;
    private String description;

    // PropertyFeatures fields
    private boolean hasGarage;
    private boolean hasPool;
    private boolean hasBalcony;
}