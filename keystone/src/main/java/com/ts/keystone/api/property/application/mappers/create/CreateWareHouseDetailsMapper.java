package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.property.domain.entity.details.WareHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateWareHouseRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateWareHouseDetailsMapper implements ICreateDetailsMapper<CreateWareHouseRequest, WareHouseDetails> {
    @Override
    public WareHouseDetails toDomain(CreateWareHouseRequest request) {
        return new WareHouseDetails(
                new Area(request.getStorageAreaValue(), request.getStorageAreaUnit()),
                new Area(request.getOfficeAreaValue(), request.getOfficeAreaUnit()),
                new Height(request.getCeilingHeightValue()),
                request.getLoadingDocks(),
                new FloorType(request.getFloorType()),
                new PropertyFeatures(false, false, false, false, false, false, false, false, request.isHasLoadingRamp())
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.WAREHOUSE;
    }
}
