package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.CountryHouseDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Description;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import org.springframework.stereotype.Component;

@Component
public class CountryHouseDetailsMapper implements DetailsMapper<CountryHouseDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.CountryHouseDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.CountryHouseDetails toJpaEntity(CountryHouseDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.CountryHouseDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setBedrooms(domainDetails.bedrooms());
        jpa.setSuites(domainDetails.suites());
        jpa.setBathrooms(domainDetails.bathrooms());
        jpa.setTotalAreaValue(domainDetails.totalArea().value());
        jpa.setTotalAreaUnit(domainDetails.totalArea().unit());
        jpa.setBuiltAreaValue(domainDetails.builtArea().value());
        jpa.setBuiltAreaUnit(domainDetails.builtArea().unit());
        jpa.setDescription(domainDetails.description().text());
        jpa.setHasGarage(domainDetails.features().hasGarage());
        jpa.setHasPool(domainDetails.features().hasPool());
        jpa.setHasRiverOrLake(domainDetails.features().hasRiverOrLake());
        return jpa;
    }

    @Override
    public CountryHouseDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.CountryHouseDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new CountryHouseDetails(
                jpaEntity.getBedrooms(),
                jpaEntity.getSuites(),
                jpaEntity.getBathrooms(),
                new Area(jpaEntity.getTotalAreaValue(), jpaEntity.getTotalAreaUnit()),
                new Area(jpaEntity.getBuiltAreaValue(), jpaEntity.getBuiltAreaUnit()),
                new Description(jpaEntity.getDescription()),
                new PropertyFeatures(jpaEntity.isHasGarage(), jpaEntity.isHasPool(), false, false, jpaEntity.isHasRiverOrLake(), false, false, false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.COUNTRY_HOUSE;
    }
}
