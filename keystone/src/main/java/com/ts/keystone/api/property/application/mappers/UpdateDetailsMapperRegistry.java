package com.ts.keystone.api.property.application.mappers;

import com.ts.keystone.api.property.application.mappers.update.IUpdateDetailsMapper;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Component
public class UpdateDetailsMapperRegistry {

    private final ApplicationContext applicationContext;
    private final Map<PropertyType, IUpdateDetailsMapper> registry = new EnumMap<>(PropertyType.class);

    public UpdateDetailsMapperRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, IUpdateDetailsMapper> mappers = applicationContext.getBeansOfType(IUpdateDetailsMapper.class);
        for (var mapper : mappers.values()) {
            registry.put(mapper.getSupportedType(), mapper);
        }
    }

    public IUpdateDetailsMapper getMapper(PropertyType type) {
        var mapper = registry.get(type);
        if (mapper == null) {
            throw new IllegalStateException("No update details mapper registered for property type: " + type);
        }
        return mapper;
    }
}
