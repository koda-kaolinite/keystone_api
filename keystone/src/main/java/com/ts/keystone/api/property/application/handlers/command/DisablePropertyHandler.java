package com.ts.keystone.api.property.application.handlers.command;


import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.commands.DisablePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DisablePropertyHandler {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void handle(DisablePropertyCommand command) {
        UUID propertyUUID = command.getPropertyUUID();
        Property property = repository.findById(propertyUUID)
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property to disable with the UUID: " + propertyUUID));

        property.disable(command.getResultFuture());

        property.publishEvents(eventPublisher);
    }
}