package com.ts.keystone.api.property.domain.events;

import com.ts.keystone.api.sharedKernel.domain.events.IDomainEvent;

import java.util.UUID;

public record PropertyDisabledEvent(UUID propertyId) implements IDomainEvent {}
