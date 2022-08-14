package com.books.books.repository;

import com.books.books.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findAllByUsername(String username);
}
