package com.books.books.model;

import com.books.books.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterModel {
    private String username;
    private String password;

    public static UserRegisterModel toModel(UserEntity user) {
        UserRegisterModel model = new UserRegisterModel();
        model.setUsername(user.getUsername());
        model.setPassword(user.getPassword());
        return model;
    }

    public UserRegisterModel() {

    }

    public UserRegisterModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
