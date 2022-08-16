package com.books.books.model.userModels;

import com.books.books.entity.UserEntity;

public class UserGetModel {
    private long id;
    private String username;

    public static UserGetModel toModel(UserEntity user) {
        UserGetModel model = new UserGetModel();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        return model;
    }

    public UserGetModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
