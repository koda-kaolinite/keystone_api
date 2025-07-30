package com.ts.keystone.api.sharedKernel.application.events.integration;

import java.util.concurrent.CompletableFuture;

public interface IIntegratedEventHandler<E, R> {
    CompletableFuture<R> handle(E integrationEvent);
}
