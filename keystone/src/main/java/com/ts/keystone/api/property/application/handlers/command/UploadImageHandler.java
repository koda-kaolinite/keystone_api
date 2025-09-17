package com.ts.keystone.api.property.application.handlers.command;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.IStorageService;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.commands.UploadImageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UploadImageHandler {

    private final IPropertyRepository repository;
    private final IStorageService storageService;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void handle(UploadImageCommand command) {
        Property property = repository.findById(command.getPropertyUUID())
                .orElseThrow(() -> new PropertyNotFound("Cannot found a property with the UUID: " + command.getPropertyUUID()));

        try {
            String imageUrl = storageService.uploadImage(command.getPropertyUUID(), command.getImageFile());
            property.addImage(imageUrl, command.getImageFile().getOriginalFilename());

            command.getResultFuture().complete(null);

            property.publishEvents(eventPublisher);

        } catch (IOException e) {
            command.getResultFuture().completeExceptionally(new RuntimeException("Failed to upload image", e));
        }
    }
}