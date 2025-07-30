package com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus;

import com.ts.keystone.api.sharedKernel.application.events.integration.IIntegratedEventHandler;
import com.ts.keystone.api.sharedKernel.application.events.integration.IIntegrationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class IntegrationEventsBus {


    private final ApplicationContext applicationContext;
    private final Map<Class<? extends IIntegrationEvent>, IIntegratedEventHandler> integrationEventHandlers = new HashMap<>();

    public IntegrationEventsBus(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void RegisterHandlers() {
        Map<String, IIntegratedEventHandler> beans = applicationContext.getBeansOfType(IIntegratedEventHandler.class);
        for (IIntegratedEventHandler integrationEventHandler : beans.values()) {
            Class<?> integrationEventType = GenericTypeResolver.resolveTypeArgument(integrationEventHandler.getClass(), IIntegratedEventHandler.class);

            if (integrationEventType != null) {
                integrationEventHandlers.put((Class<? extends IIntegrationEvent<?>>) integrationEventType, integrationEventHandler);
            }
        }
    }

    public void register(Class<? extends IIntegrationEvent> integrationEventClass, IIntegratedEventHandler handler) {
        integrationEventHandlers.put(integrationEventClass, handler);
    }

    @SuppressWarnings("unchecked")
    public <R> CompletableFuture<R> dispatch(IIntegrationEvent<R> integrationEvent) {
        IIntegratedEventHandler handler = integrationEventHandlers.get(integrationEvent.getClass());
        if (handler == null) {
            return CompletableFuture.failedFuture(new IllegalStateException("No handler for " + integrationEvent.getClass()));
        }
        return handler.handle(integrationEvent);
    }
}
