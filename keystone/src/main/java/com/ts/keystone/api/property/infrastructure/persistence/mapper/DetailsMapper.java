package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.property.PropertyType;

/**
 * Generic interface for mapping between domain detail objects and their corresponding JPA entities.
 *
 * @param <D> The domain detail type (e.g., HouseDetails).
 * @param <E> The JPA entity detail type (e.g., HouseDetails).
 */
public interface DetailsMapper<D, E> {

    /**
     * Maps a domain detail object to its JPA entity representation.
     */
    E toJpaEntity(D domainDetails);

    /**
     * Maps a JPA entity detail to its domain object representation.
     */
    D toDomain(E jpaEntity);

    /**
     * Returns the PropertyType that this mapper supports.
     */
    PropertyType getSupportedType();
}
