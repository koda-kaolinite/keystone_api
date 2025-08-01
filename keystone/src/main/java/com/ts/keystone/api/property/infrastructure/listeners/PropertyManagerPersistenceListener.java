package com.ts.keystone.api.property.infrastructure.listeners;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.events.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyManagerPersistenceListener {

    private final IPropertyRepository propertyRepository;

    @TransactionalEventListener
    public void onPropertyCreated(PropertyCreatedEvent event) {
        log.info("Handling PropertyCreatedEvent for new property ID: {}", event.property().getId());
        try {
            propertyRepository.save(event.property());
            event.future().complete(event.property().getId());
        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.property().getId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener
    public void onPropertyEnabled(PropertyEnabledEvent event) {
        log.info("Handling PropertyEnabledEvent for property ID: {}", event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);
            event.future().complete(null);
        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener
    public void onPropertyDisabled(PropertyDisabledEvent event) {
        log.info("Handling PropertyDisabledEvent for property ID: {}", event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);
            event.future().complete(null);
        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener
    public void onImageEnabled(ImageEnabledEvent event) {
        log.info("Handling ImageEnabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);
            event.future().complete(null);
        } catch (Exception e) {
            log.error("Failed to persist image {} for property {}: {}", event.imageId(), event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener
    public void onImageDisabled(ImageDisabledEvent event) {
        log.info("Handling ImageDisabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);
            event.future().complete(null);
        } catch (Exception e) {
            log.error("Failed to persist image {} for property {}: {}", event.imageId(), event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }
}
