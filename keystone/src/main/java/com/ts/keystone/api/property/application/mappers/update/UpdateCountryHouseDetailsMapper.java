package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.CountryHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateCountryHouseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCountryHouseDetailsMapper implements IUpdateDetailsMapper<UpdateCountryHouseRequest, CountryHouseDetails> {
    @Override
    public CountryHouseDetails toDomain(UpdateCountryHouseRequest request) {
        return new CountryHouseDetails(
                request.bedrooms(),
                request.suites(),
                request.bathrooms(),
                new Area(request.totalAreaValue(), request.totalAreaUnit()),
                new Area(request.builtAreaValue(), request.builtAreaUnit()),
                new Description(request.description()),
                new PropertyFeatures(request.hasGarage(), request.hasPool(), false, false, request.hasRiverOrLake(), false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.COUNTRY_HOUSE;
    }
}
