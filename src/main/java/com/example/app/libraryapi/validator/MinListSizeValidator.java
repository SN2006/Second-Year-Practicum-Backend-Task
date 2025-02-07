package com.example.app.libraryapi.validator;

import com.example.app.libraryapi.validator.annotations.MinListSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class MinListSizeValidator implements ConstraintValidator<MinListSize, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
