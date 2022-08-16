package com.books.books.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long isbn;
    private String authorName;
    @ManyToMany(mappedBy = "likedBooks")
    private List<UserEntity> addedUsers = new ArrayList<>();

    public BookEntity() {
    }

    public long getId() {
        return id;
    }

    public List<UserEntity> getAddedUsers() {
        return addedUsers;
    }

    public void setAddedUsers(List<UserEntity> addedUsers) {
        this.addedUsers = addedUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
