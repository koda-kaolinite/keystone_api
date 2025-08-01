package com.ts.keystone.api.webAdapter.property;

import com.ts.keystone.api.sharedKernel.application.events.IModuleClient;
import com.ts.keystone.api.webAdapter.property.commands.*;
import com.ts.keystone.api.webAdapter.property.queries.GetImageQuery;
import com.ts.keystone.api.webAdapter.property.queries.PageableSearchQuery;
import com.ts.keystone.api.webAdapter.property.requests.CreateHouseRequest;
import com.ts.keystone.api.webAdapter.property.requests.PageableFilters;
import com.ts.keystone.api.webAdapter.property.requests.UpdateRequest;
import com.ts.keystone.api.webAdapter.property.responses.ImageDTO;
import com.ts.keystone.api.webAdapter.property.responses.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/${api.version}/property-management")
@RequiredArgsConstructor
public class PropertyController {

    private final IModuleClient moduleClient;

    @PostMapping("/house")
    public CompletableFuture<ResponseEntity<UUID>> createHouse(@RequestBody CreateHouseRequest request) {
        var command = new CreateHouseCommand(request);
        return moduleClient.executeCommandAsync(command)
                .thenApply(ResponseEntity::ok);
    }

    @PatchMapping("/{propertyUUID}/disable")
    public CompletableFuture<ResponseEntity<Void>> disableProperty(@PathVariable UUID propertyUUID) {
        var command = new DisablePropertyCommand(propertyUUID);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.accepted().build());
    }

    @PatchMapping("/{propertyUUID}/enable")
    public CompletableFuture<ResponseEntity<Void>> enableProperty(@PathVariable UUID propertyUUID) {
        var command = new EnablePropertyCommand(propertyUUID);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.accepted().build());
    }

    @PatchMapping("/{propertyUUID}/image/{imageUUID}/disable")
    public CompletableFuture<ResponseEntity<Void>> disableImage(@PathVariable UUID imageUUID, @PathVariable UUID propertyUUID) {
        var command = new DisableImageCommand(imageUUID, propertyUUID);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyUUID}/image/{imageUUID}/enable")
    public CompletableFuture<ResponseEntity<Void>> enableImage(@PathVariable UUID imageUUID, @PathVariable UUID propertyUUID) {
        var command = new EnableImageCommand(imageUUID, propertyUUID);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyUUID}")
    public CompletableFuture<ResponseEntity<Void>> updateProperty(@RequestBody UpdateRequest request, @PathVariable UUID propertyUUID) {
        var command = new UpdatePropertyCommand(propertyUUID, request);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.accepted().build());
    }

    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<Page<PropertyDTO>>> pageableSearch(@RequestBody PageableFilters filters, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        var query = new PageableSearchQuery(filters, page, size);

        return moduleClient.executeQueryAsync(query)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/{propertyUUID}/image", consumes = "multipart/form-data")
    public CompletableFuture<ResponseEntity<Void>> uploadImage(@RequestPart(value = "image") MultipartFile imageFile, @PathVariable UUID propertyUUID) {
        var command = new UploadImageCommand(propertyUUID, imageFile);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.accepted().build());
    }

    @GetMapping("/{propertyUUID}/image/{imageUUID}")
    public CompletableFuture<ResponseEntity<ImageDTO>> ImageURL(@PathVariable UUID propertyUUID, @PathVariable UUID imageUUID) {
        var query = new GetImageQuery(propertyUUID, imageUUID);

        return moduleClient.executeQueryAsync(query)
                .thenApply(ResponseEntity::ok);
    }
}