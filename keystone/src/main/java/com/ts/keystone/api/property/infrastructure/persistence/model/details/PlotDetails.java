package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class PlotDetails {

    @Id
    private UUID id;

    public PlotDetails() {
        this.id = UUID.randomUUID();
    }
}