package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.StudioDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateStudioRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateStudioDetailsMapper implements ICreateDetailsMapper<CreateStudioRequest, StudioDetails> {
    @Override
    public StudioDetails toDomain(CreateStudioRequest request) {
        return new StudioDetails(
                new Area(request.getAreaValue(), request.getAreaUnit()),
                request.getBathroom(),
                request.getFloor(),
                new Money(request.getCondominiumFeeAmount(), request.getCondominiumFeeCurrency()),
                new PropertyFeatures(false, false, request.isHasBalcony(), false, false, false, false, request.isFurnished(), false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.STUDIO;
    }
}
