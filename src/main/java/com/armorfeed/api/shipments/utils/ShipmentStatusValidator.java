package com.armorfeed.api.shipments.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShipmentStatusValidator implements ConstraintValidator<ValidateShipmentStatus, CharSequence> {
    private List<String> validValues;

    @Override
    public void initialize(ValidateShipmentStatus constraint) {
        validValues = Stream.of(constraint.enumClass().getEnumConstants())
            .map(Enum::toString)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if(value == null) return false;
        return validValues.contains(value);
    }
    
}
