package com.ts.keystone.api.property.application.exceptions;

import com.ts.keystone.api.sharedKernel.domain.exceptions.BussinessRuleValidationException;

public class PropertyNotFound extends BussinessRuleValidationException {
    public PropertyNotFound(String message) {
        super(message);
    }
}
