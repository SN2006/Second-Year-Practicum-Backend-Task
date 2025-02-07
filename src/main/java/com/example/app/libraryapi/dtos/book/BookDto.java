package com.example.app.libraryapi.dtos.book;

import com.example.app.libraryapi.dtos.AuthorDto;
import com.example.app.libraryapi.dtos.GenreDto;

import java.util.List;

public class BookDto {

    private Long id;
    private String name;
    private String description;
    private Integer publicationYear;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

    public BookDto() {}

    public BookDto(Long id, String name, String description, Integer publicationYear, List<AuthorDto> authors, List<GenreDto> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publicationYear = publicationYear;
        this.authors = authors;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
