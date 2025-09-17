package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import com.ts.keystone.api.webAdapter.property.requests.create.CreatePropertyRequest;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class CreatePropertyCommand extends BaseCommand<UUID> {
    private final CreatePropertyRequest request;

    public CreatePropertyCommand(CreatePropertyRequest request, CompletableFuture<UUID> resultFuture) {
        super(request, resultFuture);
        this.request = request;
    }
}