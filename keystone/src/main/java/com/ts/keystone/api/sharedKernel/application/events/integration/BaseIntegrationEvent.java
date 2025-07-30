package com.ts.keystone.api.sharedKernel.application.events.integration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseIntegrationEvent<TResult> implements IIntegrationEvent<TResult> {
    private UUID commandUUID;
    private LocalDateTime dateOcurred;
}
