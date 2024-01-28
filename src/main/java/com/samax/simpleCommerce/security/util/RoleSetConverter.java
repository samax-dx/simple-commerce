package com.samax.simpleCommerce.security.util;

import com.samax.simpleCommerce.security.model.RoleName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Converter
public class RoleSetConverter implements AttributeConverter<Set<RoleName>, String> {

    @Override
    public String convertToDatabaseColumn(Set<RoleName> value) {
        return value.stream().map(RoleName::name).collect(Collectors.joining(","));
    }

    @Override
    public Set<RoleName> convertToEntityAttribute(String value) {
        return Arrays.stream(value.split(","))
                .map(RoleName::valueOf)
                .collect(Collectors.toSet());
    }

}
