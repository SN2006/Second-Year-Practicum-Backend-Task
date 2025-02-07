package com.example.app.libraryapi.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class BadRequestException extends RuntimeException {

    private final Map<String, String> errors;

    public BadRequestException(BindingResult result) {
        errors = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    public BadRequestException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getFieldsErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return errors.toString();
    }
}
