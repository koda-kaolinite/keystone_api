package com.ts.keystone.api.property.infrastructure.persistence.model.details;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Amenity;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CondominiumPlotDetails {

    @Id
    private UUID id;

    @Column(name = "area_value")
    private BigDecimal areaValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_unit")
    private AreaUnit areaUnit;

    private String dimensions;
    private String topography;
    private String condominiumName;

    @Column(name = "condominium_fee_amount")
    private BigDecimal condominiumFeeAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "condominium_fee_currency")
    private Currency condominiumFeeCurrency;

    @ElementCollection(targetClass = Amenity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "condo_plot_amenities", joinColumns = @JoinColumn(name = "details_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Set<Amenity> amenities;
}
