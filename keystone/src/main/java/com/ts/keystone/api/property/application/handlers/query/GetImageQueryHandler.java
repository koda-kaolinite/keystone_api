package com.ts.keystone.api.property.application.handlers.query;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import com.ts.keystone.api.property.domain.entity.property.Property;
import com.ts.keystone.api.webAdapter.property.queries.GetImageQuery;
import com.ts.keystone.api.webAdapter.property.responses.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetImageQueryHandler {

    private final IPropertyRepository propertyRepository;

    @EventListener
    public void handle(GetImageQuery query) {
        try {
            Property property = propertyRepository.findById(query.getPropertyUUID())
                    .orElseThrow(() -> new PropertyNotFound("Property not found with UUID: " + query.getPropertyUUID()));

            ImageDTO imageDTO = property.getImages().stream()
                    .filter(image -> image.getId().equals(query.getImageUUID()))
                    .findFirst()
                    .map(ImageDTO::createDTO)
                    .orElseThrow(() -> new PropertyNotFound("Image not found with UUID: " + query.getImageUUID()));

            query.getResultFuture().complete(imageDTO);
        } catch (Exception e) {
            query.getResultFuture().completeExceptionally(e);
        }
    }
}