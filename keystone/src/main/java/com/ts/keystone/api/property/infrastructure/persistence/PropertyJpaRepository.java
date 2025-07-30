package com.ts.keystone.api.property.infrastructure.persistence;


import com.ts.keystone.api.property.infrastructure.persistence.model.PropertyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyJpaRepository extends JpaRepository<PropertyJpaEntity, UUID> {
}
