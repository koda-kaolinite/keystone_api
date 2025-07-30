package com.ts.keystone.api.property.infrastructure.listeners;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.events.ImageDisabledEvent;
import com.ts.keystone.api.property.domain.events.ImageEnabledEvent;
import com.ts.keystone.api.property.domain.events.PropertyDisabledEvent;
import com.ts.keystone.api.property.domain.events.PropertyEnabledEvent;
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
    public void onPropertyEnabled(PropertyEnabledEvent event) {
        log.info("Handling PropertyEnabledEvent for property ID: {}", event.propertyId());
        var property = propertyRepository.findById(event.propertyId())
                .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
        propertyRepository.save(property);
    }

    @TransactionalEventListener
    public void onPropertyDisabled(PropertyDisabledEvent event) {
        log.info("Handling PropertyDisabledEvent for property ID: {}", event.propertyId());
        var property = propertyRepository.findById(event.propertyId())
                .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
        propertyRepository.save(property);
    }

    @TransactionalEventListener
    public void onImageEnabled(ImageEnabledEvent event) {
        log.info("Handling ImageEnabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        var property = propertyRepository.findById(event.propertyId())
                .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
        propertyRepository.save(property);
    }

    @TransactionalEventListener
    public void onImageDisabled(ImageDisabledEvent event) {
        log.info("Handling ImageDisabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        var property = propertyRepository.findById(event.propertyId())
                .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
        propertyRepository.save(property);
    }
}
