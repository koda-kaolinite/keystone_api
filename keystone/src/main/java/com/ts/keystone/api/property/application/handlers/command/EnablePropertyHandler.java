package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.commands.EnablePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EnablePropertyHandler {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void handle(EnablePropertyCommand command) {
        UUID propertyUUID = command.getPropertyUUID();
        Property property = repository.findById(propertyUUID)
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property to Enable with the UUID: " + propertyUUID));

        property.enable(command.getResultFuture());

        property.publishEvents(eventPublisher);
    }
}