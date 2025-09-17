package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.OthersDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateOthersRequest;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class CreateOthersDetailsMapper implements ICreateDetailsMapper<CreateOthersRequest, OthersDetails> {
    @Override
    public OthersDetails toDomain(CreateOthersRequest request) {
        return new OthersDetails(
                new Description(request.getTypeDescription()),
                new Area(request.getAreaValue(), request.getAreaUnit()),
                new YearBuilt(Year.of(request.getYearBuilt())),
                new LegalStatus(request.getLegalStatus()),
                new Description(request.getDescription())
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OTHERS;
    }
}
