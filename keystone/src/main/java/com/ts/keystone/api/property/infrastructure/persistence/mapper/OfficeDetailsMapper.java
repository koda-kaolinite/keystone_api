package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.OfficeDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Name;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import org.springframework.stereotype.Component;

@Component
public class OfficeDetailsMapper implements DetailsMapper<OfficeDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.OfficeDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.OfficeDetails toJpaEntity(OfficeDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.OfficeDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setAreaValue(domainDetails.area().value());
        jpa.setAreaUnit(domainDetails.area().unit());
        jpa.setNumberOfRooms(domainDetails.numberOfRooms());
        jpa.setBathrooms(domainDetails.bathrooms());
        jpa.setParkingSpaces(domainDetails.parkingSpaces());
        jpa.setFloor(domainDetails.floor());
        jpa.setBuildingName(domainDetails.buildingName().firstName());
        jpa.setHasReception(domainDetails.features().hasGarage()); // Assuming hasGarage maps to hasReception for office
        jpa.setHasAirConditioning(domainDetails.features().hasAirConditioning());
        return jpa;
    }

    @Override
    public OfficeDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.OfficeDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new OfficeDetails(
                new Area(jpaEntity.getAreaValue(), jpaEntity.getAreaUnit()),
                jpaEntity.getNumberOfRooms(),
                jpaEntity.getBathrooms(),
                jpaEntity.getParkingSpaces(),
                jpaEntity.getFloor(),
                new Name(jpaEntity.getBuildingName(), null),
                new PropertyFeatures(jpaEntity.isHasReception(), false, false, false, false, jpaEntity.isHasAirConditioning(), false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OFFICE;
    }
}
