package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

import java.util.Set;

public record CondominiumPlotDetails(Area area, Dimensions dimensions, Topography topography, Name condominiumName,
                                     Money condominiumFee, Set<Amenity> amenities) {
}
