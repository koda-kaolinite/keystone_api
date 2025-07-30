package com.ts.keystone.api.property.application.handlers.command;


import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.DisablePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class DisablePropertyHandler implements ICommandHandler<DisablePropertyCommand, Void> {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CompletableFuture<Void> handle(DisablePropertyCommand command) {
        return CompletableFuture.runAsync(() -> {
            UUID propertyUUID = command.getPropertyUUID();
            Property property = repository.findById(propertyUUID)
                    .orElseThrow(() -> new PropertyNotFound("Cannot found a property to disable with the UUID: " + propertyUUID));

            property.disable();

            property.publishEvents(eventPublisher);
            property.clearDomainEvents();
        });
    }
}
