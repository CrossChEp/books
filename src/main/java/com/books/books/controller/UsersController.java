package com.books.books.controller;

import com.books.books.exception.UserAlreadyExistsException;
import com.books.books.model.UserRegisterModel;
import com.books.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsersFromDatabase());
        } catch (Exception e) {
            throw e;
        }
    }
}
