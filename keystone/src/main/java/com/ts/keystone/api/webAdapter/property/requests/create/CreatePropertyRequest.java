package com.ts.keystone.api.webAdapter.property.requests.create;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import lombok.Getter;

@Getter
public class CreatePropertyRequest {

    private PropertyType propertyType;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "propertyType"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = CreateHouseRequest.class, name = "HOUSE"),
            @JsonSubTypes.Type(value = CreateCondominiumHouseRequest.class, name = "CONDOMINIUM_HOUSE"),
            @JsonSubTypes.Type(value = CreateCondominiumPlotRequest.class, name = "CONDOMINIUM_PLOT"),
            @JsonSubTypes.Type(value = CreateCountryHouseRequest.class, name = "COUNTRY_HOUSE"),
            @JsonSubTypes.Type(value = CreateOfficeRequest.class, name = "OFFICE"),
            @JsonSubTypes.Type(value = CreateOthersRequest.class, name = "OTHERS"),
            @JsonSubTypes.Type(value = CreatePentHouseRequest.class, name = "PENTHOUSE"),
            @JsonSubTypes.Type(value = CreatePlotRequest.class, name = "PLOT"),
            @JsonSubTypes.Type(value = CreateStudioRequest.class, name = "STUDIO"),
            @JsonSubTypes.Type(value = CreateWareHouseRequest.class, name = "WAREHOUSE")
    })
    private CreateDetailsRequest details;
}
