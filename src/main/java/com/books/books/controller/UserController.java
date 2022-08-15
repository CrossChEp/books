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

    @GetMapping("/current")
    public ResponseEntity test() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @DeleteMapping
    public ResponseEntity deleteUser() {
        try {
            userService.deleteUser();
            return ResponseEntity.ok("User was deleted");
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserUpdateModel updateData) {
        try {
            userService.updateUser(updateData);
            return ResponseEntity.ok("user data was updated");
        } catch (Exception e) {
            throw e;
        }
    }
}
