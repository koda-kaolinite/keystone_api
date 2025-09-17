package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateStudioRequest implements CreateDetailsRequest {
    private BigDecimal areaValue;
    private AreaUnit areaUnit;
    private int bathroom;
    private int floor;
    private BigDecimal condominiumFeeAmount;
    private Currency condominiumFeeCurrency;
    private boolean hasBalcony;
    private boolean isFurnished;
}
