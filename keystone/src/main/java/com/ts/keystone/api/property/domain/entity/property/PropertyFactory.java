package com.ts.keystone.api.property.domain.entity.property;

import com.github.f4b6a3.ulid.UlidCreator;
import com.ts.keystone.api.property.domain.entity.details.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating Property aggregates in a consistent and valid state.
 * Uses specific methods for each property type to ensure type safety.
 */
@Component
public class PropertyFactory {

    private Property createBase(PropertyType type, Object details, CompletableFuture<UUID> future) {
        Property property = new Property(
                UlidCreator.getMonotonicUlid().toUuid(),
                true, // Default active state
                Collections.emptyList(), // Properties start with no images
                type,
                details,
                future
        );
        return property;
    }

    public Property createHouse(HouseDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.HOUSE, details, future);
    }

    public Property createCondominiumHouse(CondominiumHouseDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.CONDOMINIUM_HOUSE, details, future);
    }

    public Property createCondominiumPlot(CondominiumPlotDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.CONDOMINIUM_PLOT, details, future);
    }

    public Property createCountryHouse(CountryHouseDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.COUNTRY_HOUSE, details, future);
    }

    public Property createOffice(OfficeDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.OFFICE, details, future);
    }

    public Property createOthers(OthersDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.OTHERS, details, future);
    }

    public Property createPentHouse(PentHouseDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.PENTHOUSE, details, future);
    }

    public Property createPlot(PlotDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.PLOT, details, future);
    }

    public Property createStudio(StudioDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.STUDIO, details, future);
    }

    public Property createWareHouse(WareHouseDetails details, CompletableFuture<UUID> future) {
        return createBase(PropertyType.WAREHOUSE, details, future);
    }
}
