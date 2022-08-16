package com.books.books.controller;

import com.books.books.exception.userExceptions.UserAlreadyExistsException;
import com.books.books.model.userModels.UserRegisterModel;
import com.books.books.model.userModels.UserUpdateModel;
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
    public ResponseEntity<Object> registration(@RequestBody UserRegisterModel userData) throws UserAlreadyExistsException {
        return ResponseEntity.ok(userService.addUserToDatabase(userData));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/current")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser() {
        return ResponseEntity.ok("User was deleted");
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateModel updateData) {
        return ResponseEntity.ok("user data was updated");
    }
}
