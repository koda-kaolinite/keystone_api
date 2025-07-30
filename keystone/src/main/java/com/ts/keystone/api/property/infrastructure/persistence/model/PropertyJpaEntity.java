package com.ts.keystone.api.property.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "properties")
@NoArgsConstructor
public class PropertyJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean active;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageJpaEntity> images = new ArrayList<>();

    public PropertyJpaEntity(UUID id, boolean active) {
        this.id = id;
        this.active = active;
    }

    // Métodos para manipulação de imagens (se necessário para o JPA)
    public void addImage(ImageJpaEntity image) {
        this.images.add(image);
        image.setProperty(this);
    }

    public void removeImage(ImageJpaEntity image) {
        this.images.remove(image);
        image.setProperty(null);
    }
}
