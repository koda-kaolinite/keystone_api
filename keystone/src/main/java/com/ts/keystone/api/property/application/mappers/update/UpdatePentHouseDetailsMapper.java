package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.PentHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

import com.ts.keystone.api.webAdapter.property.requests.update.UpdatePentHouseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdatePentHouseDetailsMapper implements IUpdateDetailsMapper<UpdatePentHouseRequest, PentHouseDetails> {
    @Override
    public PentHouseDetails toDomain(UpdatePentHouseRequest request) {
        return new PentHouseDetails(
                request.bedrooms(),
                request.suites(),
                request.bathrooms(),
                request.parkingSpaces(),
                new Area(request.totalAreaValue(), request.totalAreaUnit()),
                new Area(request.builtAreaValue(), request.builtAreaUnit()),
                new Area(request.terraceAreaValue(), request.terraceAreaUnit()),
                new Description(request.viewDescription()),
                new PropertyFeatures(request.hasGarage(), request.hasPool(), false, request.hasPrivateElevator(), false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.PENTHOUSE;
    }
}
