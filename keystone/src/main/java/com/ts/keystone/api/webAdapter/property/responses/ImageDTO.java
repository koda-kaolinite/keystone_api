package com.ts.keystone.api.webAdapter.property.responses;

import com.ts.keystone.api.property.domain.entity.image.Image;

public record ImageDTO(String imageURL) {
    public static ImageDTO createDTO(Image image) {
        return new ImageDTO(image.getUrl());
    }
}
