package com.ts.keystone.api.property.domain.events;

import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.domain.events.IDomainEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Event published when a new Property aggregate is successfully created.
 * It carries a CompletableFuture to signal the completion of the persistence operation.
 */
public record PropertyCreatedEvent(Property property, CompletableFuture<UUID> future) implements IDomainEvent {}
