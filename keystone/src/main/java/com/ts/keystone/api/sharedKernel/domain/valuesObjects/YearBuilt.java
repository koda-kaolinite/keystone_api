package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

import java.time.Year;

public record YearBuilt(Year value) {
    public YearBuilt {
        if (value == null) {
            throw new IllegalArgumentException("Year value cannot be null.");
        }
        if (value.isAfter(Year.now())) {
            throw new IllegalArgumentException("Year built cannot be in the future.");
        }
    }
}
