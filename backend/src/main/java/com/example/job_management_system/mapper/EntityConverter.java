package com.example.job_management_system.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter<T,D>{

    private final ModelMapper modelMapper;

    @Autowired
    public EntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public D entityToDto(T entity , Class<D> dtoClass){
        return modelMapper.map(entity,dtoClass);
    }

    public T dtoToEntity(D dto , Class<T> entityClass){
        return modelMapper.map(dto,entityClass);
    }
}