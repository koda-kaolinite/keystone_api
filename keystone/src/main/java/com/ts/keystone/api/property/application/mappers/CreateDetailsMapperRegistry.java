package com.ts.keystone.api.property.application.mappers;

import com.ts.keystone.api.property.application.mappers.create.ICreateDetailsMapper;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Component
public class CreateDetailsMapperRegistry {

    private final ApplicationContext applicationContext;
    private final Map<PropertyType, ICreateDetailsMapper> registry = new EnumMap<>(PropertyType.class);

    public CreateDetailsMapperRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, ICreateDetailsMapper> mappers = applicationContext.getBeansOfType(ICreateDetailsMapper.class);
        for (var mapper : mappers.values()) {
            registry.put(mapper.getSupportedType(), mapper);
        }
    }

    public ICreateDetailsMapper getMapper(PropertyType type) {
        var mapper = registry.get(type);
        if (mapper == null) {
            throw new IllegalStateException("No create details mapper registered for property type: " + type);
        }
        return mapper;
    }
}
