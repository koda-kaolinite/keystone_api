package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.details.HouseDetails;
import com.ts.keystone.api.property.domain.entity.property.PropertyType;
import org.springframework.stereotype.Component;

@Component
public class HouseDetailsMapper implements DetailsMapper<HouseDetails, HouseDetails> {

    @Override
    public HouseDetails toJpaEntity(HouseDetails domainDetails) {
        if (domainDetails == null) {
            return null;
        }
        return new HouseDetails(
                domainDetails.getBedrooms(),
                domainDetails.getBathrooms(),
                domainDetails.getTotalArea(),
                domainDetails.isHasGarage()
        );
    }

    @Override
    public HouseDetails toDomain(HouseDetails jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return new HouseDetails(
                jpaEntity.getBedrooms(),
                jpaEntity.getBathrooms(),
                jpaEntity.getTotalArea(),
                jpaEntity.isHasGarage()
        );
    }

    @Override
    public PropertyType getSupportedType() {
        return PropertyType.HOUSE;
    }
}
