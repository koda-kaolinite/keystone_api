package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.OthersDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Description;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.LegalStatus;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.YearBuilt;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class OthersDetailsMapper implements DetailsMapper<OthersDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.OthersDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.OthersDetails toJpaEntity(OthersDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.OthersDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setTypeDescription(domainDetails.typeDescription().text());
        jpa.setAreaValue(domainDetails.area().value());
        jpa.setAreaUnit(domainDetails.area().unit());
        jpa.setYearBuilt(domainDetails.yearBuilt().value().getValue());
        jpa.setLegalStatus(domainDetails.legalStatus().description());
        jpa.setDescription(domainDetails.description().text());
        return jpa;
    }

    @Override
    public OthersDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.OthersDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new OthersDetails(
                new Description(jpaEntity.getTypeDescription()),
                new Area(jpaEntity.getAreaValue(), jpaEntity.getAreaUnit()),
                new YearBuilt(Year.of(jpaEntity.getYearBuilt())),
                new LegalStatus(jpaEntity.getLegalStatus()),
                new Description(jpaEntity.getDescription())
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.OTHERS;
    }
}
