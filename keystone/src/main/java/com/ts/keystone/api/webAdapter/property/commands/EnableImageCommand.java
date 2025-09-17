package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class EnableImageCommand extends BaseCommand<Void> {
    private final UUID imageUUID;
    private final UUID propertyUUID;

    public EnableImageCommand(UUID imageUUID, UUID propertyUUID, CompletableFuture<Void> resultFuture) {
        super(imageUUID, resultFuture);
        this.imageUUID = imageUUID;
        this.propertyUUID = propertyUUID;
    }
}