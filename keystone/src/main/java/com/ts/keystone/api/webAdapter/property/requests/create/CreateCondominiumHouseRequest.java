package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Amenity;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateCondominiumHouseRequest implements CreateDetailsRequest {
    private int bedrooms;
    private int suites;
    private int bathrooms;
    private int parkingSpaces;
    private int floor;
    private BigDecimal totalAreaValue;
    private AreaUnit totalAreaUnit;
    private BigDecimal builtAreaValue;
    private AreaUnit builtAreaUnit;
    private String condominiumName;
    private BigDecimal condominiumFeeAmount;
    private Currency condominiumFeeCurrency;
    private Set<Amenity> amenities;
    private boolean hasGarage;
    private boolean hasBalcony;
}
