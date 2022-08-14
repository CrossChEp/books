package com.books.books.model;

import com.books.books.entity.UserEntity;

public class UserUpdateModel {
    private String username;
    private String password;

    public static UserUpdateModel toModel(UserEntity user) {
        UserUpdateModel model = new UserUpdateModel();
        model.setUsername(user.getUsername());
        model.setPassword(user.getPassword());
        return model;
    }

    public UserUpdateModel() {
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
