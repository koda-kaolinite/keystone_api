package com.ts.keystone.api.sharedKernel.infrastructure;

import com.ts.keystone.api.sharedKernel.application.events.IModuleClient;
import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import com.ts.keystone.api.sharedKernel.application.events.integration.BaseIntegrationEvent;
import com.ts.keystone.api.sharedKernel.application.events.query.BaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ModuleClient implements IModuleClient {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public <TResult> CompletableFuture<TResult> executeCommandAsync(BaseCommand<TResult> command) {
        eventPublisher.publishEvent(command);
        return command.getResultFuture();
    }

    @Override
    public <TResult> CompletableFuture<TResult> executeQueryAsync(BaseQuery<TResult> query) {
        eventPublisher.publishEvent(query);
        return query.getResultFuture();
    }

    @Override
    public <TResult> CompletableFuture<TResult> executeIntegrationEventAsync(BaseIntegrationEvent<TResult> integrationEvent) {
        eventPublisher.publishEvent(integrationEvent);
        return integrationEvent.getResultFuture();
    }
}
