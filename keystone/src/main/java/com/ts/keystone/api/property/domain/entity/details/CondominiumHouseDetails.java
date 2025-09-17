package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

import java.util.Set;

public record CondominiumHouseDetails(int bedrooms, int suites, int bathrooms, int parkingSpaces, int floor,
                                      Area totalArea, Area builtArea, Name condominiumName, Money condominiumFee,
                                      Set<Amenity> amenities, PropertyFeatures features) {
}
