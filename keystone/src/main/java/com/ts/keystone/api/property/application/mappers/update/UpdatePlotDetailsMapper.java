package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.PlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdatePlotRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdatePlotDetailsMapper implements IUpdateDetailsMapper<UpdatePlotRequest, PlotDetails> {
    @Override
    public PlotDetails toDomain(UpdatePlotRequest request) {
        return new PlotDetails(
                new Area(request.areaValue(), request.areaUnit()),
                new Dimensions(request.dimensions()),
                new Topography(request.topographyDescription()),
                new Zoning(request.zoning()),
                new PropertyFeatures(false, false, false, false, false, false, request.hasFence(), false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.PLOT;
    }
}
