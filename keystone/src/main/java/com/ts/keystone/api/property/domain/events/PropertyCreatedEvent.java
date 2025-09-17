package com.ts.keystone.api.property.domain.events;

import com.ts.keystone.api.property.domain.entity.property.Property;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public record PropertyCreatedEvent(Property property, CompletableFuture<UUID> future) {
    @Override
    public CompletableFuture<UUID> future() {
        return future;
    }
}