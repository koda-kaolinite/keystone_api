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
public class WareHouseDetails {

    @Id
    private UUID id;

    @Column(name = "storage_area_value")
    private BigDecimal storageAreaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "storage_area_unit")
    private AreaUnit storageAreaUnit;

    @Column(name = "office_area_value")
    private BigDecimal officeAreaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "office_area_unit")
    private AreaUnit officeAreaUnit;

    @Column(name = "ceiling_height_value")
    private BigDecimal ceilingHeightValue;

    private int loadingDocks;
    private String floorType;
    private boolean hasLoadingRamp;
}
