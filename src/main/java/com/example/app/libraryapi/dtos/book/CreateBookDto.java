package com.example.app.libraryapi.dtos.book;

import com.example.app.libraryapi.dtos.AuthorDto;
import com.example.app.libraryapi.dtos.GenreDto;
import com.example.app.libraryapi.validator.annotations.MinListSize;
import com.example.app.libraryapi.validator.annotations.UniqueBookName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateBookDto {

    @NotBlank(message = "Name should not be empty")
    @UniqueBookName
    private String name;
    @NotBlank(message = "Description should not be empty")
    private String description;
    @NotNull(message = "Publication year should not be empty")
    private Integer publicationYear;
    @MinListSize(message = "Book should have at least 1 author")
    private List<AuthorDto> authors;
    @MinListSize(message = "Book should have at least 1 genre")
    private List<GenreDto> genres;

    public CreateBookDto() {}

    public CreateBookDto(String name, String description, Integer publicationYear, List<AuthorDto> authors, List<GenreDto> genres) {
        this.name = name;
        this.description = description;
        this.publicationYear = publicationYear;
        this.authors = authors;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }
}
