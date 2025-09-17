package com.ts.keystone.api.property.application.mappers.create;

import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import com.ts.keystone.api.webAdapter.property.requests.create.CreateDetailsRequest;

public interface ICreateDetailsMapper<R extends CreateDetailsRequest, D> {
    D toDomain(R request);
    PropertyType getSupportedType();
}
