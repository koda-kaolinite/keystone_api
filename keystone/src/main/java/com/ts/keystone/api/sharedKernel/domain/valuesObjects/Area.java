package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

import java.math.BigDecimal;

public record Area(BigDecimal value, AreaUnit unit) {
    public Area {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Area value cannot be null or negative.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Area unit cannot be null.");
        }
    }
}