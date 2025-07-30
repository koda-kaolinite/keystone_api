package com.ts.keystone.api.sharedKernel.application.events.commands;

import java.util.concurrent.CompletableFuture;

public interface ICommandHandler<C extends ICommand<R>, R> {
    CompletableFuture<R> handle(C command);
}