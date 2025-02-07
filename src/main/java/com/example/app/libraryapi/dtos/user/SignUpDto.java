package com.example.app.libraryapi.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpDto {

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password should not be empty")
    private char[] password;

    public SignUpDto() {}

    public SignUpDto(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
