package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.PropertyAssembler;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.commands.CreatePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreatePropertyCommandHandler {

    private final PropertyAssembler propertyAssembler;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void handle(CreatePropertyCommand command) {
        Property property = propertyAssembler.assembleForCreation(command.getRequest(), command.getResultFuture());

        property.publishEvents(eventPublisher);
    }
}
