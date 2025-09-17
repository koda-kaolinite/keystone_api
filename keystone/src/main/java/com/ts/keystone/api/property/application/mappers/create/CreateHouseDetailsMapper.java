package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.HouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateHouseRequest;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class CreateHouseDetailsMapper implements ICreateDetailsMapper<CreateHouseRequest, HouseDetails> {

    @Override
    public HouseDetails toDomain(CreateHouseRequest request) {
        return new HouseDetails(
                request.getBedrooms(),
                request.getSuites(),
                request.getBathrooms(),
                request.getParkingSpaces(),
                new Area(request.getTotalAreaValue(), request.getTotalAreaUnit()),
                new Area(request.getBuiltAreaValue(), request.getBuiltAreaUnit()),
                new YearBuilt(Year.of(request.getYearBuilt())),
                new Description(request.getDescription()),
                new PropertyFeatures(request.isHasGarage(), request.isHasPool(), request.isHasBalcony(), false, false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.HOUSE;
    }
}
