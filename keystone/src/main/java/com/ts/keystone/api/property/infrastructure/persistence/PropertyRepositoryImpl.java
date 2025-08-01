package com.ts.keystone.api.property.infrastructure.persistence;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.domain.entity.image.Image;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.property.infrastructure.persistence.mapper.DetailsMapper;
import com.ts.keystone.api.property.infrastructure.persistence.mapper.DetailsMapperRegistry;
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
    private final DetailsMapperRegistry  mapperRegistry;

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

        DetailsMapper mapper = mapperRegistry.getMapper(jpaEntity.getType());
        Object details = mapper.toDomain(getJpaDetails(jpaEntity));

        return Property.fromPersistence(
                jpaEntity.getId(),
                jpaEntity.isActive(),
                images,
                jpaEntity.getType(),
                details
        );
    }

    private Object getJpaDetails(PropertyJpaEntity jpaEntity) {
        return switch (jpaEntity.getType()) {
            case HOUSE -> jpaEntity.getHouseDetails();
            case CONDOMINIUM_HOUSE -> jpaEntity.getCondominiumHouseDetails();
            case CONDOMINIUM_PLOT -> jpaEntity.getCondominiumPlotDetails();
            case COUNTRY_HOUSE -> jpaEntity.getCountryHouseDetails();
            case OFFICE -> jpaEntity.getOfficeDetails();
            case OTHERS -> jpaEntity.getOthersDetails();
            case PENTHOUSE -> jpaEntity.getPentHouseDetails();
            case PLOT -> jpaEntity.getPlotDetails();
            case STUDIO -> jpaEntity.getStudioDetails();
            case WAREHOUSE -> jpaEntity.getWareHouseDetails();
        };
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
            case HOUSE ->
                    jpaEntity.setHouseDetails(new HouseDetails()); // Mapper de Domain POJO para JpaEntity viria aqui
            case CONDOMINIUM_HOUSE -> jpaEntity.setCondominiumHouseDetails(new CondominiumHouseDetails());
            case CONDOMINIUM_PLOT -> jpaEntity.setCondominiumPlotDetails(new CondominiumPlotDetails());
            case COUNTRY_HOUSE -> jpaEntity.setCountryHouseDetails(new CountryHouseDetails());
            case OFFICE -> jpaEntity.setOfficeDetails(new OfficeDetails());
            case OTHERS -> jpaEntity.setOthersDetails(new OthersDetails());
            case PENTHOUSE -> jpaEntity.setPentHouseDetails(new PentHouseDetails());
            case PLOT -> jpaEntity.setPlotDetails(new PlotDetails());
            case STUDIO -> jpaEntity.setStudioDetails(new StudioDetails());
            case WAREHOUSE -> jpaEntity.setWareHouseDetails(new WareHouseDetails());
        }

        return jpaEntity;
    }
}
