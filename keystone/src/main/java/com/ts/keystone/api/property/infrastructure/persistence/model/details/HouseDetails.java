package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HouseDetails {

    @Id
    private UUID id;

    private int bedrooms;
    private int suites;
    private int bathrooms;
    private int parkingSpaces;

    @Column(name = "total_area_value")
    private BigDecimal totalAreaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "total_area_unit")
    private AreaUnit totalAreaUnit;

    @Column(name = "built_area_value")
    private BigDecimal builtAreaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "built_area_unit")
    private AreaUnit builtAreaUnit;

    private int yearBuilt;

    @Lob
    private String description;

    private boolean hasGarage;
    private boolean hasPool;
    private boolean hasBalcony;
}
