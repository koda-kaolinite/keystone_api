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
public class PentHouseDetails {

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

    @Column(name = "terrace_area_value")
    private BigDecimal terraceAreaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "terrace_area_unit")
    private AreaUnit terraceAreaUnit;

    @Lob
    private String viewDescription;

    private boolean hasGarage;
    private boolean hasPool;
    private boolean hasPrivateElevator;
}
