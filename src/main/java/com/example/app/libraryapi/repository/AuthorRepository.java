package com.example.app.libraryapi.repository;

import com.example.app.libraryapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFullNameIgnoreCase(String fullName);
}
