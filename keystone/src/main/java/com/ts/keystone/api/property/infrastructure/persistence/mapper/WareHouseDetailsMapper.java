package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.WareHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.FloorType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Height;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import org.springframework.stereotype.Component;

@Component
public class WareHouseDetailsMapper implements DetailsMapper<WareHouseDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.WareHouseDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.WareHouseDetails toJpaEntity(WareHouseDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.WareHouseDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setStorageAreaValue(domainDetails.storageArea().value());
        jpa.setStorageAreaUnit(domainDetails.storageArea().unit());
        jpa.setOfficeAreaValue(domainDetails.officeArea().value());
        jpa.setOfficeAreaUnit(domainDetails.officeArea().unit());
        jpa.setCeilingHeightValue(domainDetails.ceilingHeight().value());
        jpa.setLoadingDocks(domainDetails.loadingDocks());
        jpa.setFloorType(domainDetails.floorType().name());
        jpa.setHasLoadingRamp(domainDetails.features().hasLoadingRamp());
        return jpa;
    }

    @Override
    public WareHouseDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.WareHouseDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new WareHouseDetails(
                new Area(jpaEntity.getStorageAreaValue(), jpaEntity.getStorageAreaUnit()),
                new Area(jpaEntity.getOfficeAreaValue(), jpaEntity.getOfficeAreaUnit()),
                new Height(jpaEntity.getCeilingHeightValue()),
                jpaEntity.getLoadingDocks(),
                new FloorType(jpaEntity.getFloorType()),
                new PropertyFeatures(false, false, false, false, false, false, false, false, jpaEntity.isHasLoadingRamp())
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.WAREHOUSE;
    }
}
