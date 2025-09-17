package com.ts.keystone.api.webAdapter.property.requests.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import lombok.Getter;

@Getter
public class UpdatePropertyRequest {


    private PropertyType propertyType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "propertyType"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = UpdateHouseRequest.class, name = "HOUSE"),
            @JsonSubTypes.Type(value = UpdateCondominiumHouseRequest.class, name = "CONDOMINIUM_HOUSE"),
            @JsonSubTypes.Type(value = UpdateCondominiumPlotRequest.class, name = "CONDOMINIUM_PLOT"),
            @JsonSubTypes.Type(value = UpdateCountryHouseRequest.class, name = "COUNTRY_HOUSE"),
            @JsonSubTypes.Type(value = UpdateOfficeRequest.class, name = "OFFICE"),
            @JsonSubTypes.Type(value = UpdateOthersRequest.class, name = "OTHERS"),
            @JsonSubTypes.Type(value = UpdatePentHouseRequest.class, name = "PENTHOUSE"),
            @JsonSubTypes.Type(value = UpdatePlotRequest.class, name = "PLOT"),
            @JsonSubTypes.Type(value = UpdateStudioRequest.class, name = "STUDIO"),
            @JsonSubTypes.Type(value = UpdateWareHouseRequest.class, name = "WAREHOUSE")
    })
    private UpdateDetailsRequest details;
}
