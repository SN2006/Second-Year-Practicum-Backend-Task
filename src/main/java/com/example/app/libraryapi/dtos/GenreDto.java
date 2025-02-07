package com.example.app.libraryapi.dtos;

public class GenreDto {

    private String name;

    public GenreDto() {}

    public GenreDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
