package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.PentHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Description;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class PentHouseDetailsMapper implements DetailsMapper<PentHouseDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.PentHouseDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.PentHouseDetails toJpaEntity(PentHouseDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.PentHouseDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setBedrooms(domainDetails.bedrooms());
        jpa.setSuites(domainDetails.suites());
        jpa.setBathrooms(domainDetails.bathrooms());
        jpa.setParkingSpaces(domainDetails.parkingSpaces());
        jpa.setTotalAreaValue(domainDetails.totalArea().value());
        jpa.setTotalAreaUnit(domainDetails.totalArea().unit());
        jpa.setBuiltAreaValue(domainDetails.builtArea().value());
        jpa.setBuiltAreaUnit(domainDetails.builtArea().unit());
        jpa.setTerraceAreaValue(domainDetails.terraceArea().value());
        jpa.setTerraceAreaUnit(domainDetails.terraceArea().unit());
        jpa.setViewDescription(domainDetails.viewDescription().text());
        jpa.setHasGarage(domainDetails.features().hasGarage());
        jpa.setHasPool(domainDetails.features().hasPool());
        jpa.setHasPrivateElevator(domainDetails.features().hasPrivateElevator());
        return jpa;
    }

    @Override
    public PentHouseDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.PentHouseDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new PentHouseDetails(
                jpaEntity.getBedrooms(),
                jpaEntity.getSuites(),
                jpaEntity.getBathrooms(),
                jpaEntity.getParkingSpaces(),
                new Area(jpaEntity.getTotalAreaValue(), jpaEntity.getTotalAreaUnit()),
                new Area(jpaEntity.getBuiltAreaValue(), jpaEntity.getBuiltAreaUnit()),
                new Area(jpaEntity.getTerraceAreaValue(), jpaEntity.getTerraceAreaUnit()),
                new Description(jpaEntity.getViewDescription()),
                new PropertyFeatures(jpaEntity.isHasGarage(), jpaEntity.isHasPool(), false, jpaEntity.isHasPrivateElevator(), false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.PENTHOUSE;
    }
}
