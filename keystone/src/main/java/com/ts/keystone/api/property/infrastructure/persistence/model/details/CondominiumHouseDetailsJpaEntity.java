package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class CondominiumHouseDetailsJpaEntity {

    @Id
    private UUID id;

    public CondominiumHouseDetailsJpaEntity() {
        this.id = UUID.randomUUID();
    }
}