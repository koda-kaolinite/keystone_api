package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.EnablePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class EnablePropertyHandler implements ICommandHandler<EnablePropertyCommand, Void> {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CompletableFuture<Void> handle(EnablePropertyCommand command) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();

        UUID propertyUUID = command.getPropertyUUID();
        Property property = repository.findById(propertyUUID)
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property to Enable with the UUID: " + propertyUUID));

        property.enable(resultFuture);

        property.publishEvents(eventPublisher);
        property.clearDomainEvents();

        return resultFuture;
    }
}
