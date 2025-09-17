package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record LegalStatus(String description) {
    public LegalStatus {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Legal status description cannot be null or blank.");
        }
    }
}
