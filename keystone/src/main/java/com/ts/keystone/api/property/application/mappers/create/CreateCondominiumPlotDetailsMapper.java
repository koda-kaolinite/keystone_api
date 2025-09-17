package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.CondominiumPlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateCondominiumPlotRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCondominiumPlotDetailsMapper implements ICreateDetailsMapper<CreateCondominiumPlotRequest, CondominiumPlotDetails> {
    @Override
    public CondominiumPlotDetails toDomain(CreateCondominiumPlotRequest request) {
        return new CondominiumPlotDetails(
                new Area(request.getAreaValue(), request.getAreaUnit()),
                new Dimensions(request.getDimensions()),
                new Topography(request.getTopography()),
                new Name(request.getCondominiumName(), null),
                new Money(request.getCondominiumFeeAmount(), request.getCondominiumFeeCurrency()),
                request.getAmenities()
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.CONDOMINIUM_PLOT;
    }
}
