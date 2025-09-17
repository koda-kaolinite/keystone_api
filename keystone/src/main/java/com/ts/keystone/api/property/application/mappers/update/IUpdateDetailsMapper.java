package com.ts.keystone.api.property.application.mappers.update;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.webAdapter.property.requests.update.UpdateDetailsRequest;


public interface IUpdateDetailsMapper<R extends UpdateDetailsRequest, D> {
    D toDomain(R request);
    PropertyType getSupportedType();
}
