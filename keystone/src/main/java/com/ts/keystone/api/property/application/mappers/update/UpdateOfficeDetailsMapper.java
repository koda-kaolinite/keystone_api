package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.OfficeDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateOfficeRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateOfficeDetailsMapper implements IUpdateDetailsMapper<UpdateOfficeRequest, OfficeDetails> {
    @Override
    public OfficeDetails toDomain(UpdateOfficeRequest request) {
        return new OfficeDetails(
                new Area(request.areaValue(), request.areaUnit()),
                request.numberOfRooms(),
                request.bathrooms(),
                request.parkingSpaces(),
                request.floor(),
                new Name(request.buildingName(), null),
                new PropertyFeatures(request.hasReception(), false, false, false, false, request.hasAirConditioning(), false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OFFICE;
    }
}
