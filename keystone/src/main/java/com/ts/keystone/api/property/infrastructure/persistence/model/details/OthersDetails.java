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
public class OthersDetails {

    @Id
    private UUID id;

    private String typeDescription;

    @Column(name = "area_value")
    private BigDecimal areaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_unit")
    private AreaUnit areaUnit;

    private int yearBuilt;
    private String legalStatus;

    @Lob
    private String description;
}
