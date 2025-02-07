package com.example.app.libraryapi.dtos.user;

import jakarta.validation.constraints.NotNull;

public class CredentialsDto {
    @NotNull
    private String email;
    @NotNull
    private char[] password;

    public CredentialsDto() {}

    public CredentialsDto(String email, @NotNull char[] password) {
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
