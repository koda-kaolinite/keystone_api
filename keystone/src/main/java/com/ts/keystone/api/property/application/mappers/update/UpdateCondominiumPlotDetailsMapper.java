package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.CondominiumPlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;

import com.ts.keystone.api.webAdapter.property.requests.update.UpdateCondominiumPlotRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCondominiumPlotDetailsMapper implements IUpdateDetailsMapper<UpdateCondominiumPlotRequest, CondominiumPlotDetails> {
    @Override
    public CondominiumPlotDetails toDomain(UpdateCondominiumPlotRequest request) {
        return new CondominiumPlotDetails(
                new Area(request.areaValue(), request.areaUnit()),
                new Dimensions(request.dimensions()),
                new Topography(request.topography()),
                new Name(request.condominiumName(), null),
                new Money(request.condominiumFeeAmount(), request.condominiumFeeCurrency()),
                request.amenities()
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.CONDOMINIUM_PLOT;
    }
}
