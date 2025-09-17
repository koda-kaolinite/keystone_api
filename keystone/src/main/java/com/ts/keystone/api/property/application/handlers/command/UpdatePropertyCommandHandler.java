package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.PropertyAssembler;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.commands.UpdatePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdatePropertyCommandHandler {

    private final IPropertyRepository propertyRepository;
    private final PropertyAssembler propertyAssembler;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void handle(UpdatePropertyCommand command) {
        Property existingProperty = propertyRepository.findById(command.getPropertyId())
                .orElseThrow(() -> new PropertyNotFound("Property not found: " + command.getPropertyId()));

        propertyAssembler.assembleForUpdate(existingProperty, command.getRequest(), command.getResultFuture());

        existingProperty.publishEvents(eventPublisher);
    }
}
