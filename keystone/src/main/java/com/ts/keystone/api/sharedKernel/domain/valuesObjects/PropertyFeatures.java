package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public record PropertyFeatures(
    boolean hasGarage,
    boolean hasPool,
    boolean hasBalcony,
    boolean hasPrivateElevator,
    boolean hasRiverOrLake,
    boolean hasAirConditioning,
    boolean hasFence,
    boolean isFurnished,
    boolean hasLoadingRamp
) {}
