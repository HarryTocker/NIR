package com.find.law.portal.mappers;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GenericBeanMapper {
    private final DozerBeanMapper mapper;

    public GenericBeanMapper() {
        this.mapper = new DozerBeanMapper();
        mapper.addMapping(new DozerBeanMappingBuilder());
    }

    public <T> T map(Object source, Class<T> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public <T> Collection<T> map(Collection<?> source, Class<T> destinationClass) {
        return source.stream().map(element -> map(element, destinationClass)).toList();
    }
}
