package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.CondominiumHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class CondominiumHouseDetailsMapper implements DetailsMapper<CondominiumHouseDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumHouseDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumHouseDetails toJpaEntity(CondominiumHouseDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumHouseDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setBedrooms(domainDetails.bedrooms());
        jpa.setSuites(domainDetails.suites());
        jpa.setBathrooms(domainDetails.bathrooms());
        jpa.setParkingSpaces(domainDetails.parkingSpaces());
        jpa.setFloor(domainDetails.floor());
        jpa.setTotalAreaValue(domainDetails.totalArea().value());
        jpa.setTotalAreaUnit(domainDetails.totalArea().unit());
        jpa.setBuiltAreaValue(domainDetails.builtArea().value());
        jpa.setBuiltAreaUnit(domainDetails.builtArea().unit());
        jpa.setCondominiumName(domainDetails.condominiumName().firstName());
        jpa.setCondominiumFeeAmount(domainDetails.condominiumFee().amount());
        jpa.setCondominiumFeeCurrency(domainDetails.condominiumFee().currency());
        jpa.setAmenities(domainDetails.amenities());
        jpa.setHasGarage(domainDetails.features().hasGarage());
        jpa.setHasBalcony(domainDetails.features().hasBalcony());
        return jpa;
    }

    @Override
    public CondominiumHouseDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumHouseDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new CondominiumHouseDetails(
                jpaEntity.getBedrooms(),
                jpaEntity.getSuites(),
                jpaEntity.getBathrooms(),
                jpaEntity.getParkingSpaces(),
                jpaEntity.getFloor(),
                new Area(jpaEntity.getTotalAreaValue(), jpaEntity.getTotalAreaUnit()),
                new Area(jpaEntity.getBuiltAreaValue(), jpaEntity.getBuiltAreaUnit()),
                new Name(jpaEntity.getCondominiumName(), null),
                new Money(jpaEntity.getCondominiumFeeAmount(), jpaEntity.getCondominiumFeeCurrency()),
                jpaEntity.getAmenities(),
                new PropertyFeatures(jpaEntity.isHasGarage(), false, jpaEntity.isHasBalcony(), false, false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.CONDOMINIUM_HOUSE;
    }
}
