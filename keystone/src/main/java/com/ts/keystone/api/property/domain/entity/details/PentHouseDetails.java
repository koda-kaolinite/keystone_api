package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Description;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;

public record PentHouseDetails(int bedrooms, int suites, int bathrooms, int parkingSpaces, Area totalArea,
                               Area builtArea, Area terraceArea, Description viewDescription,
                               PropertyFeatures features) {
}
