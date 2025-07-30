package com.ts.keystone.api.webAdapter.property;

import com.ts.keystone.api.property.application.exceptions.PropertyNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PropertyExceptionHandler {

    @ExceptionHandler(PropertyNotFound.class)
    public ResponseEntity<String> propertyNotFound(PropertyNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
