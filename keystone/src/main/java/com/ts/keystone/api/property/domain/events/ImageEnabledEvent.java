package com.ts.keystone.api.property.domain.events;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public record ImageEnabledEvent(UUID propertyId, UUID imageId, CompletableFuture<Void> future) {
    @Override
    public CompletableFuture<Void> future() {
        return future;
    }
}