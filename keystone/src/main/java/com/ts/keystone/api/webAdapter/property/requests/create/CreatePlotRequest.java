package com.ts.keystone.api.webAdapter.property.requests.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.AreaUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePlotRequest implements CreateDetailsRequest {
    private BigDecimal areaValue;
    private AreaUnit areaUnit;
    private String dimensions;
    private String topographyDescription;
    private String zoning;
    private boolean hasFence;
}
