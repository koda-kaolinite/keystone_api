package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import com.ts.keystone.api.webAdapter.property.requests.update.ChangeStatusRequest;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class ChangeStatusCommand extends BaseCommand<Void> {
    private final UUID propertyUUID;
    private final ChangeStatusRequest request;

    public ChangeStatusCommand(UUID propertyUUID, ChangeStatusRequest request, CompletableFuture<Void> resultFuture) {
        super(propertyUUID, resultFuture);
        this.propertyUUID = propertyUUID;
        this.request = request;
    }
}