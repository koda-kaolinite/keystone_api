package com.ts.keystone.api.webAdapter.property.responses;

import com.ts.keystone.api.property.domain.entity.property.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private UUID id;
    private boolean active;
    private List<ImageDTO> images;

    public static PropertyDTO convert(Property property) {
        List<ImageDTO> imageDTOS = property.getImages().stream()
                .map(ImageDTO::createDTO)
                .collect(Collectors.toList());
        return new PropertyDTO(property.getId(), property.isActive(), imageDTOS);
    }
}
