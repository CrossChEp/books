package com.books.books.controller;

import com.books.books.entity.UserEntity;
import com.books.books.exception.UserAlreadyExistsException;
import com.books.books.model.UserRegisterModel;
import com.books.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity registration(@RequestBody UserRegisterModel userData) throws UserAlreadyExistsException {
        try {
            return ResponseEntity.ok(userService.addUserToDatabase(userData));
        } catch (UserAlreadyExistsException e) {
            throw e;
        }
    }
}
