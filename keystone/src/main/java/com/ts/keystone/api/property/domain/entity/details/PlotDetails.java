package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Dimensions;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Topography;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Zoning;

public record PlotDetails(Area area, Dimensions dimensions, Topography topography, Zoning zoning,
                          PropertyFeatures features) {
}
