package com.ts.keystone.api.sharedKernel.application.events.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public abstract class BaseQuery<TResult> extends ApplicationEvent implements IQuery<TResult> {
    private final UUID queryUUID;
    private final LocalDateTime dateOcurred;
    private final CompletableFuture<TResult> resultFuture;

    protected BaseQuery(Object source, CompletableFuture<TResult> resultFuture) {
        super(source);
        this.queryUUID = UUID.randomUUID();
        this.dateOcurred = LocalDateTime.now();
        this.resultFuture = resultFuture;
    }
}