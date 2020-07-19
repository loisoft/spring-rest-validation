package com.example.demo.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class CityConstraintValidator implements ConstraintValidator<ValidCity, String> {
    private Set<String> acceptCities;

    public CityConstraintValidator() {
        acceptCities = new HashSet<>();
        acceptCities.add("HCMC");
        acceptCities.add("BRVT");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptCities.contains(value);
    }
}