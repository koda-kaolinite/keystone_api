package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

public record CountryHouseDetails(int bedrooms, int suites, int bathrooms, Area totalArea, Area builtArea,
                                  Description description, PropertyFeatures features) {
}
