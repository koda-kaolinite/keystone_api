package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Amenity;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateCondominiumPlotRequest implements CreateDetailsRequest {
    private BigDecimal areaValue;
    private AreaUnit areaUnit;
    private String dimensions;
    private String topography;
    private String condominiumName;
    private BigDecimal condominiumFeeAmount;
    private Currency condominiumFeeCurrency;
    private Set<Amenity> amenities;
}
