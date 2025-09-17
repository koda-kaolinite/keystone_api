package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdatePropertyRequest;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class UpdatePropertyCommand extends BaseCommand<Void> {
    private final UUID propertyId;
    private final UpdatePropertyRequest request;

    public UpdatePropertyCommand(UUID propertyId, UpdatePropertyRequest request, CompletableFuture<Void> resultFuture) {
        super(request, resultFuture);
        this.propertyId = propertyId;
        this.request = request;
    }
}
