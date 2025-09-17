package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class UploadImageCommand extends BaseCommand<Void> {
    private final UUID propertyUUID;
    private final MultipartFile imageFile;

    public UploadImageCommand(UUID propertyUUID, MultipartFile imageFile, CompletableFuture<Void> resultFuture) {
        super(propertyUUID, resultFuture);
        this.propertyUUID = propertyUUID;
        this.imageFile = imageFile;
    }
}