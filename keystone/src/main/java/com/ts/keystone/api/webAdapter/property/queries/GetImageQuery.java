package com.ts.keystone.api.webAdapter.property.queries;

import com.ts.keystone.api.webAdapter.property.responses.ImageDTO;
import com.ts.keystone.api.sharedKernel.application.events.query.BaseQuery;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class GetImageQuery extends BaseQuery<ImageDTO> {
    private final UUID propertyUUID;
    private final UUID imageUUID;

    public GetImageQuery(UUID propertyUUID, UUID imageUUID, CompletableFuture<ImageDTO> resultFuture) {
        super(propertyUUID, resultFuture);
        this.propertyUUID = propertyUUID;
        this.imageUUID = imageUUID;
    }
}