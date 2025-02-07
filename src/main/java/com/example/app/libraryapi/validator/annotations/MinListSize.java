package com.example.app.libraryapi.validator.annotations;

import com.example.app.libraryapi.validator.MinListSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinListSizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinListSize {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
