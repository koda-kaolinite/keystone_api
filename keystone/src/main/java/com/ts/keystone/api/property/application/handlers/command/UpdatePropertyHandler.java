package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.UpdatePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UpdatePropertyHandler implements ICommandHandler<UpdatePropertyCommand, Void> {

    private final IPropertyRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CompletableFuture<Void> handle(UpdatePropertyCommand command) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();

        Property property = repository.findById(command.getPropertyUUID())
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

        // A lógica de update não registra eventos de domínio por padrão, mas se registrasse,
        // o future seria passado para o evento e completado no listener.
        property.update(command.getUpdateRequest());

        // Se o update gerar eventos, eles seriam publicados aqui.
        // property.publishEvents(eventPublisher);
        // property.clearDomainEvents();

        // Como o updateRequest está vazio e não gera eventos, completamos o future aqui.
        // Em um cenário real, se o update gerasse eventos, o future seria completado no listener.
        resultFuture.complete(null);

        return resultFuture;
    }
}
