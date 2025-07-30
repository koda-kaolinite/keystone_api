package com.ts.keystone.api.sharedKernel.infrastructure;

import com.ts.keystone.api.sharedKernel.application.events.IModuleClient;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommand;
import com.ts.keystone.api.sharedKernel.application.events.integration.IIntegrationEvent;
import com.ts.keystone.api.sharedKernel.application.events.query.IQuery;
import com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus.CommandBus;
import com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus.IntegrationEventsBus;
import com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ModuleClient implements IModuleClient {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final IntegrationEventsBus integrationEventBus;

    @Override
    public <TResult> CompletableFuture<TResult> executeCommandAsync(ICommand<TResult> command) {
        return commandBus.dispatch(command);
    }

    @Override
    public <TResult> CompletableFuture<TResult> executeQueryAsync(IQuery<TResult> query) {
        return queryBus.dispatch(query);
    }

    @Override
    public <TResult> CompletableFuture<TResult> executeIntegrationEventAsync(IIntegrationEvent<TResult> integrationEvent) {
        return integrationEventBus.dispatch(integrationEvent);
    }
}
