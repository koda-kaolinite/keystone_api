package com.ts.keystone.api.webAdapter.property.requests;

import lombok.Getter;

@Getter
public class CreateHouseRequest {
    // Aqui iriam os campos comuns a todas as propriedades, ex:
    // private AddressRequest address;
    // private MoneyRequest price;

    // Campos específicos para House
    private int bedrooms;
    private int bathrooms;
    private double totalArea;
    private boolean hasGarage;
}
