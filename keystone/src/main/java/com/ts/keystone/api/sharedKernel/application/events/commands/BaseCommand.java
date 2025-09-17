package com.ts.keystone.api.sharedKernel.application.events.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public abstract class BaseCommand<TResult> extends ApplicationEvent implements ICommand<TResult> {
    private final UUID commandUUID;
    private final LocalDateTime dateOcurred;
    private final CompletableFuture<TResult> resultFuture;

    protected BaseCommand(Object source, CompletableFuture<TResult> resultFuture) {
        super(source);
        this.commandUUID = UUID.randomUUID();
        this.dateOcurred = LocalDateTime.now();
        this.resultFuture = resultFuture;
    }
}