package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) {
    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative.");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null.");
        }
    }
}