package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.domain.entity.details.HouseDetails;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.property.domain.entity.property.PropertyFactory;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.CreateHouseCommand;
import com.ts.keystone.api.webAdapter.property.requests.CreateHouseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CreateHouseCommandHandler implements ICommandHandler<CreateHouseCommand, UUID> {

    private final PropertyFactory propertyFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CompletableFuture<UUID> handle(CreateHouseCommand command) {
        CreateHouseRequest request = command.getCreateHouseRequest();

        HouseDetails details = new HouseDetails(
                request.getBedrooms(),
                request.getBathrooms(),
                request.getTotalArea(),
                request.isHasGarage()
        );

        // Cria um CompletableFuture que será completado pelo listener
        CompletableFuture<UUID> resultFuture = new CompletableFuture<>();

        // A factory cria o agregado e já registra o PropertyCreatedEvent com o future
        Property property = propertyFactory.createHouse(details, resultFuture);

        // O handler publica os eventos registrados no agregado
        property.publishEvents(eventPublisher);
        property.clearDomainEvents();

        // Retorna o future, que será completado quando a persistência for concluída
        return resultFuture;
    }
}

