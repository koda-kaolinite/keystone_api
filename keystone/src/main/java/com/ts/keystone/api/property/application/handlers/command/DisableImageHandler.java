package com.ts.keystone.api.property.application.handlers.command;


import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.DisableImageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class DisableImageHandler implements ICommandHandler<DisableImageCommand, Void> {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CompletableFuture<Void> handle(DisableImageCommand command) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();

        Property property = repository.findById(command.getPropertyUUID())
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

        property.disableImage(command.getImageUUID(), resultFuture);

        property.publishEvents(eventPublisher);
        property.clearDomainEvents();

        return resultFuture;
    }
}
