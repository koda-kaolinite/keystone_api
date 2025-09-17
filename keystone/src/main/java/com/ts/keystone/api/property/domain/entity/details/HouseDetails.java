package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

public record HouseDetails(int bedrooms, int suites, int bathrooms, int parkingSpaces, Area totalArea, Area builtArea,
                           YearBuilt yearBuilt, Description description, PropertyFeatures features) {
}
