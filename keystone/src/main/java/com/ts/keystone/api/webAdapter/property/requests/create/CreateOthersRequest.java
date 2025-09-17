package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateOthersRequest implements CreateDetailsRequest {
    private String typeDescription;
    private BigDecimal areaValue;
    private AreaUnit areaUnit;
    private int yearBuilt;
    private String legalStatus;
    private String description;
}
