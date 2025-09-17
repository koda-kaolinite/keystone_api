package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class DisablePropertyCommand extends BaseCommand<Void> {
    private final UUID propertyUUID;

    public DisablePropertyCommand(UUID propertyUUID, CompletableFuture<Void> resultFuture) {
        super(propertyUUID, resultFuture);
        this.propertyUUID = propertyUUID;
    }
}