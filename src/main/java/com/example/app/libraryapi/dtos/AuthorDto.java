package com.example.app.libraryapi.dtos;

public class AuthorDto {

    private String fullName;

    public AuthorDto() {}

    public AuthorDto(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
