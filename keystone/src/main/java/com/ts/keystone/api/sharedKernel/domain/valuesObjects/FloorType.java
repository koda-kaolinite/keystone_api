package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record FloorType(String name) {
    public FloorType {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Floor type name cannot be null or blank.");
        }
    }
}
