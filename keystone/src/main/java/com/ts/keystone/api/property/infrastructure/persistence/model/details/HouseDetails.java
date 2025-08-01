package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
public class HouseDetails {

    @Id
    private UUID id;
    int bedrooms;
    int bathrooms;
    double totalArea;
    boolean hasGarage;

    public HouseDetails() {
        this.id = UUID.randomUUID();
    }

    public HouseDetails(int bedrooms, int bathrooms, double totalArea, boolean hasGarage) {
    }
}