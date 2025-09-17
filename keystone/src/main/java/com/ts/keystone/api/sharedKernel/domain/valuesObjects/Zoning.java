package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record Zoning(String description) {
    public Zoning {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Zoning description cannot be null or blank.");
        }
    }
}
