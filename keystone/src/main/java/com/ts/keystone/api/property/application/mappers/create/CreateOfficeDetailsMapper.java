package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.OfficeDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateOfficeRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateOfficeDetailsMapper implements ICreateDetailsMapper<CreateOfficeRequest, OfficeDetails> {
    @Override
    public OfficeDetails toDomain(CreateOfficeRequest request) {
        return new OfficeDetails(
                new Area(request.getAreaValue(), request.getAreaUnit()),
                request.getNumberOfRooms(),
                request.getBathrooms(),
                request.getParkingSpaces(),
                request.getFloor(),
                new Name(request.getBuildingName(), null),
                new PropertyFeatures(request.isHasReception(), false, false, false, false, request.isHasAirConditioning(), false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OFFICE;
    }
}
