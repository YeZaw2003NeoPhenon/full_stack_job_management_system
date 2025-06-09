package com.example.job_management_system.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DataMapper<T,R> implements Function<T,R> {
    private final Function<T,R> functionMapping;

    @Autowired
    public DataMapper(Function<T, R> functionMapping) {
        this.functionMapping = functionMapping;
    }

    @Override
    public R apply(T t) {
        return functionMapping.apply(t);
    }
}