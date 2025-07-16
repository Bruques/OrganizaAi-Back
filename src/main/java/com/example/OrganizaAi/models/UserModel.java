package com.example.OrganizaAi.models;

import org.springframework.data.annotation.Id;

public class UserModel {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public UserModel() {}
    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
