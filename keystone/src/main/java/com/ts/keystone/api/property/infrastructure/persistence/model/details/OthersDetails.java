package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class OthersDetails {

    @Id
    private UUID id;

    public OthersDetails() {
        this.id = UUID.randomUUID();
    }
}