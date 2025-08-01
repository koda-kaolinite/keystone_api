package com.ts.keystone.api.property.domain.entity.property;

import com.github.f4b6a3.ulid.UlidCreator;
import com.ts.keystone.api.property.domain.entity.image.Image;
import com.ts.keystone.api.property.domain.events.*;
import com.ts.keystone.api.sharedKernel.domain.AggregateRoot;
import com.ts.keystone.api.webAdapter.property.requests.UpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
public class Property extends AggregateRoot {

    private final UUID id;
    private boolean active;
    private final List<Image> images;
    private final PropertyType type;
    private final Object details; // Generic object to hold specific details of property

    // Factory Constructor
    Property(UUID id, boolean active, List<Image> images, PropertyType type, Object details, CompletableFuture<UUID> future) {
        this.id = id;
        this.active = active;
        this.images = images;
        this.type = type;
        this.details = details;

        registerEvent(new PropertyCreatedEvent(this, future));
    }

    // Metodo estático de fábrica para reconstrução a partir da persistência (usado pelo Repository)
    public static Property fromPersistence(UUID id, boolean active, List<Image> images, PropertyType type, Object details) {
        return new Property(id, active, images, type, details);
    }

    public void disable(CompletableFuture<Void> future) {
        this.active = false;
        registerEvent(new PropertyDisabledEvent(this.id, future));
    }

    public void enable(CompletableFuture<Void> future) {
        this.active = true;
        registerEvent(new PropertyEnabledEvent(this.id, future));
    }

    public void addImage(String url, String description) {
        Image newImage = new Image(UlidCreator.getMonotonicUlid().toUuid(), this.id, url, description, true);
        this.images.add(newImage);
    }

    public void enableImage(UUID imageId, CompletableFuture<Void> future) {
        findImageById(imageId).ifPresent(image -> {
            image.enable();
            registerEvent(new ImageEnabledEvent(this.id, imageId, future));
        });
    }

    public void disableImage(UUID imageId, CompletableFuture<Void> future) {
        findImageById(imageId).ifPresent(image -> {
            image.disable();
            registerEvent(new ImageDisabledEvent(this.id, imageId, future));
        });
    }

    private Optional<Image> findImageById(UUID imageId) {
        return this.images.stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst();
    }


    public void update(UpdateRequest request) {
        // Logic to update the property will be implemented here
    }

    public void publishEvents(ApplicationEventPublisher publisher) {
        this.domainEvents().forEach(publisher::publishEvent);
    }

    public void clearDomainEvents() {
        this.domainEvents().clear();
    }
}