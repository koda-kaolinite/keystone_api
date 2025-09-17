package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.property.domain.entity.details.CondominiumHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateCondominiumHouseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCondominiumHouseDetailsMapper implements IUpdateDetailsMapper<UpdateCondominiumHouseRequest, CondominiumHouseDetails> {
    @Override
    public CondominiumHouseDetails toDomain(UpdateCondominiumHouseRequest request) {
        return new CondominiumHouseDetails(
                request.bedrooms(),
                request.suites(),
                request.bathrooms(),
                request.parkingSpaces(),
                request.floor(),
                new Area(request.totalAreaValue(), request.totalAreaUnit()),
                new Area(request.builtAreaValue(), request.builtAreaUnit()),
                new Name(request.condominiumName(), null),
                new Money(request.condominiumFeeAmount(), request.condominiumFeeCurrency()),
                request.amenities(),
                new PropertyFeatures(request.hasGarage(), false, request.hasBalcony(), false, false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.CONDOMINIUM_HOUSE;
    }
}