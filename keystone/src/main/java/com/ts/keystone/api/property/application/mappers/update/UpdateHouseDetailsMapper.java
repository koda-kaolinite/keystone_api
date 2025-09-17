package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.HouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateHouseRequest;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class UpdateHouseDetailsMapper implements IUpdateDetailsMapper<UpdateHouseRequest, HouseDetails> {

    @Override
    public HouseDetails toDomain(UpdateHouseRequest request) {
        return new HouseDetails(
                request.bedrooms(),
                request.suites(),
                request.bathrooms(),
                request.parkingSpaces(),
                new Area(request.totalAreaValue(), request.totalAreaUnit()),
                new Area(request.builtAreaValue(), request.builtAreaUnit()),
                new YearBuilt(Year.of(request.yearBuilt())),
                new Description(request.description()),
                new PropertyFeatures(request.hasGarage(), request.hasPool(), request.hasBalcony(), false, false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.HOUSE;
    }
}
