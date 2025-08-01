package com.ts.keystone.api.property.domain.entity.details;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HouseDetails {
    private final int bedrooms;
    private final int bathrooms;
    private final double totalArea;
    private final boolean hasGarage;
}