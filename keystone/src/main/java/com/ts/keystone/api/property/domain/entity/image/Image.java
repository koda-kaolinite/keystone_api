package com.ts.keystone.api.property.domain.entity.image;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Image {

    private final UUID id;
    private final UUID propertyId;
    private final String url;
    private String description;
    private boolean enabled;

    public Image(UUID id, UUID propertyId, String url, String description, boolean enabled) {
        this.id = id;
        this.propertyId = propertyId;
        this.url = url;
        this.description = description;
        this.enabled = enabled;
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }
}