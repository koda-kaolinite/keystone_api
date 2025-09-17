package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.OthersDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateOthersRequest;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class UpdateOthersDetailsMapper implements IUpdateDetailsMapper<UpdateOthersRequest, OthersDetails> {
    @Override
    public OthersDetails toDomain(UpdateOthersRequest request) {
        return new OthersDetails(
                new Description(request.typeDescription()),
                new Area(request.areaValue(), request.areaUnit()),
                new YearBuilt(Year.of(request.yearBuilt())),
                new LegalStatus(request.legalStatus()),
                new Description(request.description())
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OTHERS;
    }
}
