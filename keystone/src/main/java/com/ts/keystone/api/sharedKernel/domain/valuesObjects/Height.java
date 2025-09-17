package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

import java.math.BigDecimal;

public record Height(BigDecimal value) {
    public Height {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Height value must be positive.");
        }
    }
}
