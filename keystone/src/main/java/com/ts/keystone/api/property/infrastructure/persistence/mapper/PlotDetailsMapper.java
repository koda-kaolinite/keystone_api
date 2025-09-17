package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.PlotDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.*;
import org.springframework.stereotype.Component;

@Component
public class PlotDetailsMapper implements DetailsMapper<PlotDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.PlotDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.PlotDetails toJpaEntity(PlotDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.PlotDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setAreaValue(domainDetails.area().value());
        jpa.setAreaUnit(domainDetails.area().unit());
        jpa.setDimensions(domainDetails.dimensions().value());
        jpa.setTopographyDescription(domainDetails.topography().description());
        jpa.setZoning(domainDetails.zoning().description());
        jpa.setHasFence(domainDetails.features().hasFence());
        return jpa;
    }

    @Override
    public PlotDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.PlotDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new PlotDetails(
                new Area(jpaEntity.getAreaValue(), jpaEntity.getAreaUnit()),
                new Dimensions(jpaEntity.getDimensions()),
                new Topography(jpaEntity.getTopographyDescription()),
                new Zoning(jpaEntity.getZoning()),
                new PropertyFeatures(false, false, false, false, false, false, jpaEntity.isHasFence(), false, false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.PLOT;
    }
}
