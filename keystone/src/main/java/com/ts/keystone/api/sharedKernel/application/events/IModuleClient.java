package com.ts.keystone.api.sharedKernel.application.events;



import com.ts.keystone.api.sharedKernel.application.events.commands.ICommand;
import com.ts.keystone.api.sharedKernel.application.events.integration.IIntegrationEvent;
import com.ts.keystone.api.sharedKernel.application.events.query.IQuery;

import java.util.concurrent.CompletableFuture;

public interface IModuleClient {
    <TResult> CompletableFuture<TResult> executeCommandAsync(ICommand<TResult> command);

    <TResult> CompletableFuture<TResult> executeQueryAsync(IQuery<TResult> query);

    <TResult> CompletableFuture<TResult> executeIntegrationEventAsync(IIntegrationEvent<TResult> IntegrationEvent);
}
