package com.books.books.exception.userExceptions;

public class UserHaveNoPermission extends Exception {
    public UserHaveNoPermission(String message) {
        super(message);
    }
}
