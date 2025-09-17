package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record Topography(String description) {
     public Topography {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Topography description cannot be null or blank.");
        }
    }
}
