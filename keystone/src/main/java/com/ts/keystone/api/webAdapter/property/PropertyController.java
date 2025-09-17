package com.ts.keystone.api.webAdapter.property;

import com.ts.keystone.api.sharedKernel.application.events.IModuleClient;
import com.ts.keystone.api.webAdapter.property.commands.*;
import com.ts.keystone.api.webAdapter.property.queries.GetImageQuery;
import com.ts.keystone.api.webAdapter.property.queries.PageableSearchQuery;
import com.ts.keystone.api.webAdapter.property.requests.PageableFilters;
import com.ts.keystone.api.webAdapter.property.requests.create.CreatePropertyRequest;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdatePropertyRequest;
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

    @PostMapping
    public CompletableFuture<ResponseEntity<UUID>> createProperty(@RequestBody CreatePropertyRequest request) {
        CompletableFuture<UUID> resultFuture = new CompletableFuture<>();
        var command = new CreatePropertyCommand(request, resultFuture);
        return moduleClient.executeCommandAsync(command)
                .thenApply(ResponseEntity::ok);
    }

    @PatchMapping("/{propertyUUID}/disable")
    public CompletableFuture<ResponseEntity<Void>> disableProperty(@PathVariable UUID propertyUUID) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new DisablePropertyCommand(propertyUUID, resultFuture);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyUUID}/enable")
    public CompletableFuture<ResponseEntity<Void>> enableProperty(@PathVariable UUID propertyUUID) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new EnablePropertyCommand(propertyUUID, resultFuture);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyUUID}/image/{imageUUID}/disable")
    public CompletableFuture<ResponseEntity<Void>> disableImage(@PathVariable UUID imageUUID, @PathVariable UUID propertyUUID) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new DisableImageCommand(imageUUID, propertyUUID, resultFuture);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyUUID}/image/{imageUUID}/enable")
    public CompletableFuture<ResponseEntity<Void>> enableImage(@PathVariable UUID imageUUID, @PathVariable UUID propertyUUID) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new EnableImageCommand(imageUUID, propertyUUID, resultFuture);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @PatchMapping("/{propertyId}")
    public CompletableFuture<ResponseEntity<Void>> updateProperty(@PathVariable UUID propertyId, @RequestBody UpdatePropertyRequest request) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new com.ts.keystone.api.webAdapter.property.commands.UpdatePropertyCommand(propertyId, request, resultFuture);
        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<Page<PropertyDTO>>> pageableSearch(@RequestBody PageableFilters filters, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        CompletableFuture<Page<PropertyDTO>> resultFuture = new CompletableFuture<>();
        var query = new PageableSearchQuery(filters, page, size, resultFuture);

        return moduleClient.executeQueryAsync(query)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/{propertyUUID}/image", consumes = "multipart/form-data")
    public CompletableFuture<ResponseEntity<Void>> uploadImage(@RequestPart(value = "image") MultipartFile imageFile, @PathVariable UUID propertyUUID) {
        CompletableFuture<Void> resultFuture = new CompletableFuture<>();
        var command = new UploadImageCommand(propertyUUID, imageFile, resultFuture);

        return moduleClient.executeCommandAsync(command)
                .thenApply(result -> ResponseEntity.ok().build());
    }

    @GetMapping("/{propertyUUID}/image/{imageUUID}")
    public CompletableFuture<ResponseEntity<ImageDTO>> ImageURL(@PathVariable UUID propertyUUID, @PathVariable UUID imageUUID) {
        CompletableFuture<ImageDTO> resultFuture = new CompletableFuture<>();
        var query = new GetImageQuery(propertyUUID, imageUUID, resultFuture);

        return moduleClient.executeQueryAsync(query)
                .thenApply(ResponseEntity::ok);
    }
}
