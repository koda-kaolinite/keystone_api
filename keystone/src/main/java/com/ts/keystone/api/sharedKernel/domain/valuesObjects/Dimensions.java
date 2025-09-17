package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record Dimensions(String value) {
    public Dimensions {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Dimensions value cannot be null or blank.");
        }
    }
}
