package com.ts.keystone.api.property.application.handlers.query;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.sharedKernel.application.events.query.IQueryHandler;
import com.ts.keystone.api.webAdapter.property.queries.GetImageQuery;
import com.ts.keystone.api.webAdapter.property.responses.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetImageQueryHandler implements IQueryHandler<GetImageQuery, ImageDTO> {

    private final IPropertyRepository propertyRepository;

    @Override
    public CompletableFuture<ImageDTO> handle(GetImageQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            Property property = propertyRepository.findById(query.getPropertyUUID())
                    .orElseThrow(() -> new PropertyNotFound("Property not found with UUID: " + query.getPropertyUUID()));

            return property.getImages().stream()
                    .filter(image -> image.getId().equals(query.getImageUUID()))
                    .findFirst()
                    .map(ImageDTO::createDTO)
                    .orElseThrow(() -> new PropertyNotFound("Image not found with UUID: " + query.getImageUUID()));
        });
    }
}
