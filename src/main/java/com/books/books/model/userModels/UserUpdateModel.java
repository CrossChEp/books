package com.books.books.model.userModels;

import com.books.books.entity.UserEntity;

public class UserUpdateModel {
    private String password;

    public static UserUpdateModel toModel(UserEntity user) {
        UserUpdateModel model = new UserUpdateModel();
        model.setPassword(user.getPassword());
        return model;
    }

    public UserUpdateModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
