package com.example.app.libraryapi.validator.annotations;

import com.example.app.libraryapi.validator.UniqueBookNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UniqueBookNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBookName {
    String message() default "Book with this name already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
