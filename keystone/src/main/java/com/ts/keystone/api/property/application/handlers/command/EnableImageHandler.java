package com.ts.keystone.api.property.application.handlers.command;


import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.EnableImageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class EnableImageHandler implements ICommandHandler<EnableImageCommand, Void> {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CompletableFuture<Void> handle(EnableImageCommand command) {
        return CompletableFuture.runAsync(() -> {
            Property property = repository.findById(command.getPropertyUUID())
                    .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

            property.enableImage(command.getImageUUID());

            repository.save(property);

            property.publishEvents(eventPublisher);
            property.clearDomainEvents();
        });
    }
}
