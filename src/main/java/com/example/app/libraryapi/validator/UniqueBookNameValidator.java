package com.example.app.libraryapi.validator;

import com.example.app.libraryapi.repository.BookRepository;
import com.example.app.libraryapi.validator.annotations.UniqueBookName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueBookNameValidator implements ConstraintValidator<UniqueBookName, String> {

    private final BookRepository bookRepository;

    @Autowired
    public UniqueBookNameValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return bookRepository.findByName(name).isEmpty();
    }
}
