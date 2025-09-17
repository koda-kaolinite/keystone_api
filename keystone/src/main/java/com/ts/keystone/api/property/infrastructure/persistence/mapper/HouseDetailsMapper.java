package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.HouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import org.springframework.stereotype.Component;

import java.time.Year;

@SuppressWarnings("ALL")
@Component
public class HouseDetailsMapper implements DetailsMapper<HouseDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.HouseDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.HouseDetails toJpaEntity(HouseDetails domainDetails) {
        if (domainDetails == null) {
            return null;
        }
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.HouseDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setBedrooms(domainDetails.bedrooms());
        jpa.setSuites(domainDetails.suites());
        jpa.setBathrooms(domainDetails.bathrooms());
        jpa.setParkingSpaces(domainDetails.parkingSpaces());
        jpa.setTotalAreaValue(domainDetails.totalArea().value());
        jpa.setTotalAreaUnit(domainDetails.totalArea().unit());
        jpa.setBuiltAreaValue(domainDetails.builtArea().value());
        jpa.setBuiltAreaUnit(domainDetails.builtArea().unit());
        jpa.setYearBuilt(domainDetails.yearBuilt().value().getValue());
        jpa.setDescription(domainDetails.description().text());
        jpa.setHasGarage(domainDetails.features().hasGarage());
        jpa.setHasPool(domainDetails.features().hasPool());
        jpa.setHasBalcony(domainDetails.features().hasBalcony());
        return jpa;
    }

    @Override
    public HouseDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.HouseDetails jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return new HouseDetails(
                jpaEntity.getBedrooms(),
                jpaEntity.getSuites(),
                jpaEntity.getBathrooms(),
                jpaEntity.getParkingSpaces(),
                new Area(jpaEntity.getTotalAreaValue(), jpaEntity.getTotalAreaUnit()),
                new Area(jpaEntity.getBuiltAreaValue(), jpaEntity.getBuiltAreaUnit()),
                new YearBuilt(Year.of(jpaEntity.getYearBuilt())),
                new Description(jpaEntity.getDescription()),
                new PropertyFeatures(jpaEntity.isHasGarage(), jpaEntity.isHasPool(), jpaEntity.isHasBalcony(), false, false, false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.HOUSE;
    }
}