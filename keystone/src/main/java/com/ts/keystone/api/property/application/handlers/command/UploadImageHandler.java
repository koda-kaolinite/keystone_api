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

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UploadImageHandler implements ICommandHandler<UploadImageCommand, Void> {

    private final IPropertyRepository repository;
    private final IStorageService storageService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CompletableFuture<Void> handle(UploadImageCommand command) {
        return CompletableFuture.runAsync(() -> {
            Property property = repository.findById(command.getPropertyUUID())
                    .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

            try {
                String imageUrl = storageService.uploadImage(command.getPropertyUUID(), command.getImageFile());
                property.addImage(imageUrl, command.getImageFile().getOriginalFilename());
                repository.save(property);
                property.publishEvents(eventPublisher);
                property.clearDomainEvents();
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }
        });
    }
}
