package com.ts.keystone.api.property.domain.events;

import com.ts.keystone.api.sharedKernel.domain.events.IDomainEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public record ImageDisabledEvent(UUID propertyId, UUID imageId, CompletableFuture<Void> future) implements IDomainEvent {}
