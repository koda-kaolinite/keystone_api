package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.StudioDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateStudioRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudioDetailsMapper implements IUpdateDetailsMapper<UpdateStudioRequest, StudioDetails> {
    @Override
    public StudioDetails toDomain(UpdateStudioRequest request) {
        return new StudioDetails(
                new Area(request.areaValue(), request.areaUnit()),
                request.bathroom(),
                request.floor(),
                new Money(request.condominiumFeeAmount(), request.condominiumFeeCurrency()),
                new PropertyFeatures(false, false, request.hasBalcony(), false, false, false, false, request.isFurnished(), false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.STUDIO;
    }
}
