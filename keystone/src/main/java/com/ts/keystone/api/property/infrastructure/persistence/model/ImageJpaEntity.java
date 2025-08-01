package com.ts.keystone.api.property.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
public class ImageJpaEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private PropertyJpaEntity property;

    private String url;
    private String description;
    private boolean enabled;

    public ImageJpaEntity(UUID id, PropertyJpaEntity property, String url, String description, boolean enabled) {
        this.id = id;
        this.property = property;
        this.url = url;
        this.description = description;
        this.enabled = enabled;
    }
}
