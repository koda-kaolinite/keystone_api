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
public class CondominiumHouseDetails {

    @Id
    private UUID id;

    private int bedrooms;
    private int suites;
    private int bathrooms;
    private int parkingSpaces;
    private int floor;

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

    private String condominiumName;

    @Column(name = "condominium_fee_amount")
    private BigDecimal condominiumFeeAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "condominium_fee_currency")
    private Currency condominiumFeeCurrency;

    @ElementCollection(targetClass = Amenity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "condo_house_amenities", joinColumns = @JoinColumn(name = "details_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Set<Amenity> amenities;

    private boolean hasGarage;
    private boolean hasBalcony;
}
