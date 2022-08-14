package com.books.books.controller;

import com.books.books.exception.UserAlreadyExistsException;
import com.books.books.model.UserRegisterModel;
import com.books.books.model.UserUpdateModel;
import com.books.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserRegisterModel userData) throws UserAlreadyExistsException {
        try {
            return ResponseEntity.ok(userService.addUserToDatabase(userData));
        } catch (UserAlreadyExistsException e) {
            throw e;
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User with id " + userId + " was deleted");
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @RequestBody UserUpdateModel updateData) {
        try {
            userService.updateUser(userId, updateData);
            return ResponseEntity.ok("user with id " + userId + " was updated");
        } catch (Exception e) {
            throw e;
        }
    }
}
