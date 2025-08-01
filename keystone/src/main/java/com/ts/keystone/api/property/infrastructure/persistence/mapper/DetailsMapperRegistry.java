package com.ts.keystone.api.property.infrastructure.persistence.mapper;

import com.ts.keystone.api.property.domain.entity.property.PropertyType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Component
public class DetailsMapperRegistry {

    private final ApplicationContext applicationContext;
    private final Map<PropertyType, DetailsMapper> registry = new EnumMap<>(PropertyType.class);

    public DetailsMapperRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, DetailsMapper> mappers = applicationContext.getBeansOfType(DetailsMapper.class);
        for (DetailsMapper mapper : mappers.values()) {
            registry.put(mapper.getSupportedType(), mapper);
        }
    }

    public DetailsMapper getMapper(PropertyType type) {
        DetailsMapper mapper = registry.get(type);
        if (mapper == null) {
            throw new IllegalStateException("No mapper registered for property type: " + type);
        }
        return mapper;
    }
}

