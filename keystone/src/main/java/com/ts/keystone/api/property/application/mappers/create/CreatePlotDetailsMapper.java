package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.PlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreatePlotRequest;
import org.springframework.stereotype.Component;

@Component
public class CreatePlotDetailsMapper implements ICreateDetailsMapper<CreatePlotRequest, PlotDetails> {
    @Override
    public PlotDetails toDomain(CreatePlotRequest request) {
        return new PlotDetails(
                new Area(request.getAreaValue(), request.getAreaUnit()),
                new Dimensions(request.getDimensions()),
                new Topography(request.getTopographyDescription()),
                new Zoning(request.getZoning()),
                new PropertyFeatures(false, false, false, false, false, false, request.isHasFence(), false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.PLOT;
    }
}
