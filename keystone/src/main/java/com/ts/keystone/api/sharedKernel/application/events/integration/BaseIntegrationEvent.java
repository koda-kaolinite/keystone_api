package com.ts.keystone.api.sharedKernel.application.events.integration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public abstract class BaseIntegrationEvent<TResult> extends ApplicationEvent implements IIntegrationEvent<TResult> {
    private final UUID eventUUID;
    private final LocalDateTime dateOcurred;
    private final CompletableFuture<TResult> resultFuture;

    protected BaseIntegrationEvent(Object source, CompletableFuture<TResult> resultFuture) {
        super(source);
        this.eventUUID = UUID.randomUUID();
        this.dateOcurred = LocalDateTime.now();
        this.resultFuture = resultFuture;
    }
}