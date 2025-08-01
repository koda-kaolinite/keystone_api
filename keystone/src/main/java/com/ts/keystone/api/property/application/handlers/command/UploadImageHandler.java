package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.IStorageService;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import com.ts.keystone.api.webAdapter.property.commands.UploadImageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UploadImageHandler implements ICommandHandler<UploadImageCommand, Void> {

    private final IPropertyRepository repository;
    private final IStorageService storageService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CompletableFuture<Void> handle(UploadImageCommand command) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();

        Property property = repository.findById(command.getPropertyUUID())
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

        try {
            String imageUrl = storageService.uploadImage(command.getPropertyUUID(), command.getImageFile());
            property.addImage(imageUrl, command.getImageFile().getOriginalFilename());

            // Se o addImage gerasse um evento, o future seria passado para ele.
            // Como não gera, completamos o future aqui.
            resultFuture.complete(null);

            property.publishEvents(eventPublisher);
            property.clearDomainEvents();

        } catch (IOException e) {
            resultFuture.completeExceptionally(new RuntimeException("Failed to upload image", e));
        }
        return resultFuture;
    }
}
