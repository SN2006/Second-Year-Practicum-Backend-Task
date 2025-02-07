package com.example.app.libraryapi.dtos.user;

import com.example.app.libraryapi.dtos.book.BookDto;
import com.example.app.libraryapi.entity.enums.Role;

import java.util.List;

public class UserDto {

    private Long id;
    private String email;
    private Role role;
    private String token;
    private List<BookDto> favouriteBooks;

    public UserDto() {}

    public UserDto(Long id, String email, Role role, String token, List<BookDto> favouriteBooks) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.token = token;
        this.favouriteBooks = favouriteBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<BookDto> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteBooks(List<BookDto> favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }
}
