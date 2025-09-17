package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.StudioDetails;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Area;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Money;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyFeatures;
import org.springframework.stereotype.Component;

@Component
public class StudioDetailsMapper implements DetailsMapper<StudioDetails, com.ts.keystone.api.property.infrastructure.persistence.model.details.StudioDetails> {

    @Override
    public com.ts.keystone.api.property.infrastructure.persistence.model.details.StudioDetails toJpaEntity(StudioDetails domainDetails) {
        if (domainDetails == null) return null;
        var jpa = new com.ts.keystone.api.property.infrastructure.persistence.model.details.StudioDetails();
        jpa.setId(com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid().toUuid());
        jpa.setAreaValue(domainDetails.area().value());
        jpa.setAreaUnit(domainDetails.area().unit());
        jpa.setBathroom(domainDetails.bathroom());
        jpa.setFloor(domainDetails.floor());
        jpa.setCondominiumFeeAmount(domainDetails.condominiumFee().amount());
        jpa.setCondominiumFeeCurrency(domainDetails.condominiumFee().currency());
        jpa.setHasBalcony(domainDetails.features().hasBalcony());
        jpa.setFurnished(domainDetails.features().isFurnished());
        return jpa;
    }

    @Override
    public StudioDetails toDomain(com.ts.keystone.api.property.infrastructure.persistence.model.details.StudioDetails jpaEntity) {
        if (jpaEntity == null) return null;
        return new StudioDetails(
                new Area(jpaEntity.getAreaValue(), jpaEntity.getAreaUnit()),
                jpaEntity.getBathroom(),
                jpaEntity.getFloor(),
                new Money(jpaEntity.getCondominiumFeeAmount(), jpaEntity.getCondominiumFeeCurrency()),
                new PropertyFeatures(false, false, jpaEntity.isHasBalcony(), false, false, false, false, jpaEntity.isFurnished(), false)
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.STUDIO;
    }
}
