package com.books.books.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_books",
            joinColumns = @JoinColumn(name = "user_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "book_entity_id")
    )
    private List<BookEntity> likedBooks = new ArrayList<>();

    public UserEntity() {
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public long getId() {
        return id;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
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

    public List<BookEntity> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<BookEntity> likedBooks) {
        this.likedBooks = likedBooks;
    }
}
