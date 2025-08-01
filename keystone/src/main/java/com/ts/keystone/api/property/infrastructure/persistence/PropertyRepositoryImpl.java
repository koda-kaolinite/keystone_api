package com.ts.keystone.api.property.infrastructure.persistence;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.domain.entity.details.*;
import com.ts.keystone.api.property.domain.entity.image.Image;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.property.infrastructure.persistence.model.ImageJpaEntity;
import com.ts.keystone.api.property.infrastructure.persistence.model.PropertyJpaEntity;
import com.ts.keystone.api.property.infrastructure.persistence.model.details.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements IPropertyRepository {

    private final PropertyJpaRepository jpaRepository;

    @Override
    public Optional<Property> findById(UUID propertyId) {
        return jpaRepository.findById(propertyId).map(this::toDomain);
    }

    @Override
    public Property save(Property property) {
        PropertyJpaEntity jpaEntity = toJpaEntity(property);
        PropertyJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return toDomain(savedEntity);
    }

    @Override
    public Page<Property> findAll(Pageable pageable) {
        Page<PropertyJpaEntity> jpaEntities = jpaRepository.findAll(pageable);
        List<Property> domainProperties = jpaEntities.getContent().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        return new PageImpl<>(domainProperties, pageable, jpaEntities.getTotalElements());
    }

    private Property toDomain(PropertyJpaEntity jpaEntity) {
        List<Image> images = jpaEntity.getImages().stream()
                .map(img -> new Image(img.getId(), jpaEntity.getId(), img.getUrl(), img.getDescription(), img.isEnabled()))
                .collect(Collectors.toList());

        Object details = switch (jpaEntity.getType()) {
            case HOUSE -> new HouseDetails(); // Mapper de JpaEntity para Domain POJO viria aqui
            case CONDOMINIUM_HOUSE -> new CondominiumHouseDetails();
            case CONDOMINIUM_PLOT -> new CondominiumPlotDetails();
            case COUNTRY_HOUSE -> new CountryHouseDetails();
            case OFFICE -> new OfficeDetails();
            case OTHERS -> new OthersDetails();
            case PENTHOUSE -> new PentHouseDetails();
            case PLOT -> new PlotDetails();
            case STUDIO -> new StudioDetails();
            case WAREHOUSE -> new WareHouseDetails();
            default -> null;
        };

        return new Property(
                jpaEntity.getId(),
                jpaEntity.isActive(),
                images,
                jpaEntity.getType(),
                details
        );
    }

    private PropertyJpaEntity toJpaEntity(Property domainProperty) {
        PropertyJpaEntity jpaEntity = new PropertyJpaEntity(
                domainProperty.getId(),
                domainProperty.isActive(),
                domainProperty.getType()
        );

        List<ImageJpaEntity> imageJpaEntities = domainProperty.getImages().stream()
                .map(img -> new ImageJpaEntity(img.getId(), jpaEntity, img.getUrl(), img.getDescription(), img.isEnabled()))
                .collect(Collectors.toList());
        jpaEntity.setImages(imageJpaEntities);

        switch (domainProperty.getType()) {
            case HOUSE -> jpaEntity.setHouseDetails(new HouseDetailsJpaEntity()); // Mapper de Domain POJO para JpaEntity viria aqui
            case CONDOMINIUM_HOUSE -> jpaEntity.setCondominiumHouseDetails(new CondominiumHouseDetailsJpaEntity());
            case CONDOMINIUM_PLOT -> jpaEntity.setCondominiumPlotDetails(new CondominiumPlotDetailsJpaEntity());
            case COUNTRY_HOUSE -> jpaEntity.setCountryHouseDetails(new CountryHouseDetailsJpaEntity());
            case OFFICE -> jpaEntity.setOfficeDetails(new OfficeDetailsJpaEntity());
            case OTHERS -> jpaEntity.setOthersDetails(new OthersDetailsJpaEntity());
            case PENTHOUSE -> jpaEntity.setPentHouseDetails(new PentHouseDetailsJpaEntity());
            case PLOT -> jpaEntity.setPlotDetails(new PlotDetailsJpaEntity());
            case STUDIO -> jpaEntity.setStudioDetails(new StudioDetailsJpaEntity());
            case WAREHOUSE -> jpaEntity.setWareHouseDetails(new WareHouseDetailsJpaEntity());
        }

        return jpaEntity;
    }
}
