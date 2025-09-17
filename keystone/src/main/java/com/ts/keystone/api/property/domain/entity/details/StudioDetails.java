package com.ts.keystone.api.property.domain.entity.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Money;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;

public record StudioDetails(Area area, int bathroom, int floor, Money condominiumFee, PropertyFeatures features) {
}
