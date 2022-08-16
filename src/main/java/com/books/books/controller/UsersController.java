package com.books.books.controller;

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
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(userService.getUsersFromDatabase());
    }
}
