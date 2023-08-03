package com.engineer.library.service;

import jakarta.validation.constraints.NotNull;

public class UserCredentialsRequest {
    private String email;
    private String password;

    public UserCredentialsRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
