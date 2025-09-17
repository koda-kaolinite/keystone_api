package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.CondominiumPlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import org.springframework.stereotype.Component;

@Component
public class CondominiumPlotDetailsMapper implements DetailsMapper<CondominiumPlotDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumPlotDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumPlotDetails toJpaEntity(CondominiumPlotDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumPlotDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setAreaValue(domainDetails.area().value());
        jpa.setAreaUnit(domainDetails.area().unit());
        jpa.setDimensions(domainDetails.dimensions().value());
        jpa.setTopography(domainDetails.topography().description());
        jpa.setCondominiumName(domainDetails.condominiumName().firstName());
        jpa.setCondominiumFeeAmount(domainDetails.condominiumFee().amount());
        jpa.setCondominiumFeeCurrency(domainDetails.condominiumFee().currency());
        jpa.setAmenities(domainDetails.amenities());
        return jpa;
    }

    @Override
    public CondominiumPlotDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.CondominiumPlotDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new CondominiumPlotDetails(
                new Area(jpaEntity.getAreaValue(), jpaEntity.getAreaUnit()),
                new Dimensions(jpaEntity.getDimensions()),
                new Topography(jpaEntity.getTopography()),
                new Name(jpaEntity.getCondominiumName(), null),
                new Money(jpaEntity.getCondominiumFeeAmount(), jpaEntity.getCondominiumFeeCurrency()),
                jpaEntity.getAmenities()
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.CONDOMINIUM_PLOT;
    }
}
