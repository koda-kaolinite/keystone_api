package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Name;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;

public record OfficeDetails(Area area, int numberOfRooms, int bathrooms, int parkingSpaces, int floor,
                            Name buildingName, PropertyFeatures features) {
}
