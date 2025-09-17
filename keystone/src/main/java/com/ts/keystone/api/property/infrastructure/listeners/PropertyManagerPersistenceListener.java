package com.ts.keystone.api.property.infrastructure.listeners;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.events.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SuppressWarnings("ALL")
@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyManagerPersistenceListener {

    private final IPropertyRepository propertyRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPropertyCreated(PropertyCreatedEvent event) {
        log.info("Handling PropertyCreatedEvent for new property ID: {}", event.property().getId());
        try {
            propertyRepository.save(event.property());

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(event.property().getId());
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, property not persisted."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.property().getId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPropertyEnabled(PropertyEnabledEvent event) {
        log.info("Handling PropertyEnabledEvent for property ID: {}", event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(null);
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, property not enabled."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPropertyDisabled(PropertyDisabledEvent event) {
        log.info("Handling PropertyDisabledEvent for property ID: {}", event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(null);
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, property not disabled."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist property {}: {}", event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onImageEnabled(ImageEnabledEvent event) {
        log.info("Handling ImageEnabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(null);
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, image not enabled."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist image {} for property {}: {}", event.imageId(), event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onImageDisabled(ImageDisabledEvent event) {
        log.info("Handling ImageDisabledEvent for image ID: {} in property ID: {}", event.imageId(), event.propertyId());
        try {
            var property = propertyRepository.findById(event.propertyId())
                    .orElseThrow(() -> new PropertyNotFound("Event received for a non-existent property. ID: " + event.propertyId()));
            propertyRepository.save(property);

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(null);
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, image not disabled."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist image {} for property {}: {}", event.imageId(), event.propertyId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onPropertyUpdated(PropertyUpdatedEvent event) {
        log.info("Handling PropertyUpdatedEvent for property ID: {}", event.property().getId());
        try {
            propertyRepository.save(event.property());

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    event.future().complete(null);
                }

                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                        event.future().completeExceptionally(new RuntimeException("Transaction rolled back, property not updated."));
                    }
                }
            });

        } catch (Exception e) {
            log.error("Failed to persist property update {}: {}", event.property().getId(), e.getMessage());
            event.future().completeExceptionally(e);
        }
    }
}
