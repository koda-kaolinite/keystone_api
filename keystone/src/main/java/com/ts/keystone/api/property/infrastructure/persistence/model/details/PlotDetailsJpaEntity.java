package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class PlotDetailsJpaEntity {

    @Id
    private UUID id;

    public PlotDetailsJpaEntity() {
        this.id = UUID.randomUUID();
    }
}